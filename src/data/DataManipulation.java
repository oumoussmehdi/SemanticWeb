package data;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soumaya Haj Youssef on 09/01/2017.
 */
public class DataManipulation {
    public static List<Person> readPersonRdfFile(String fileName, String ontologyPrefix) {
        // create a model
        Model model = createModel(fileName);

        // convert RDF data to list of person
        List<Person> person = loadPersonData(model, ontologyPrefix);
        return person;
    }

    public static List<Person> loadPersonData(Model model, String ontologyPrefix) {
        List<Person> persons = new ArrayList<>();
        //loop over all persons
        // Create a new query
        String queryString = "PREFIX ontology_base: <http://www.okkam.org/" + ontologyPrefix + "> " +

                "SELECT ?person ?given_name ?surname ?age ?date_of_birth ?soc_sec_id ?phone_numer " +
                "?has_address ?street ?house_number ?postcode ?is_in_suburb ?name_suburb ?is_in_state ?name_state " +
                "WHERE {" +
                "      ?person ontology_base:given_name ?given_name . " +
                "      ?person ontology_base:surname ?surname ." +
                "      ?person ontology_base:age ?age ." +
                "      ?person ontology_base:date_of_birth ?date_of_birth ." +
                "      ?person ontology_base:soc_sec_id ?soc_sec_id ." +
                "      ?person ontology_base:phone_numer ?phone_numer ." +
                "      ?person ontology_base:has_address ?has_address ." +
                "      ?has_address ontology_base:street ?street ." +
                "      ?has_address ontology_base:house_number ?house_number ." +
                "      ?has_address ontology_base:postcode ?postcode .";

        if (ontologyPrefix.contains("person1")) {
            queryString = queryString +
                    "      ?has_address ontology_base:is_in_suburb ?is_in_suburb ." +
                    "      ?is_in_suburb ontology_base:name ?name_suburb ." +
                    "      ?is_in_suburb ontology_base:is_in_state ?is_in_state ." +
                    "      ?is_in_state ontology_base:name ?name_state ." +
                    "      }";


        } else if (ontologyPrefix.contains("person2")) {
            queryString = queryString +

                    "      ?has_address ontology_base:suburb ?name_suburb ." +
                    "      ?has_address ontology_base:state ?name_state ." +
                    "      }";
        }
        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        // Output query results
        //ResultSetFormatter.out(System.out, results, query);

        while (results.hasNext()) {
            Person person = new Person();
            QuerySolution sol = results.next();
            person.setURI(sol.get("person").toString());
            person.setGiven_name(sol.get("given_name").toString());
            person.setSurname(sol.get("surname").toString());
            person.setAge(sol.get("age").toString());
            person.setDate_of_birth(sol.get("date_of_birth").toString());
            person.setSocial_security_number(sol.get("soc_sec_id").toString());
            person.setPhone_number(sol.get("phone_numer").toString());
            person.setStreet(sol.get("street").toString());
            person.setHouse_number(sol.get("house_number").toString());
            person.setPostcode(sol.get("postcode").toString());
            person.setSuburb_name(sol.get("name_suburb").toString());
            person.setState(sol.get("name_state").toString());
            //System.out.println(person.toString());
            persons.add(person);
        }


        // Important - free up resources used running the query
        qe.close();
        model.close();
        return persons;
    }

    public static List<Restaurant> readRestaurantRdfFile(String fileName, String ontologyPrefix) {
        Model model = createModel(fileName);

        // convert RDF data to list of person
        List<Restaurant> restaurants = loadRestaurantData(model, ontologyPrefix);
        return restaurants;
    }

    public static List<Restaurant> loadRestaurantData(Model model, String ontologyPrefix) {
        List<Restaurant> restaurants = new ArrayList<>();

        //loop over all restaurants
        // Create a new query
        String queryString = "PREFIX ontology_base: <http://www.okkam.org/" + ontologyPrefix + "> " +

                "SELECT ?restaurant ?name ?phone_number ?category ?has_category" +
                "?has_address ?street ?is_in_city ?name_city " +
                "WHERE {" +
                "      ?restaurant ontology_base:name ?name . " +
                "      ?restaurant ontology_base:phone_number ?phone_number ." +
                "      ?restaurant ontology_base:has_address ?has_address ." +
                "      ?has_address ontology_base:street ?street .";

        if (ontologyPrefix.contains("restaurant1")) {
            queryString = queryString +
                    "      ?restaurant ontology_base:category ?category ." +
                    "      ?has_address ontology_base:is_in_city ?is_in_city ." +
                    "      ?is_in_city ontology_base:name ?name_city ." +
                    "      }";


        } else if (ontologyPrefix.contains("restaurant2")) {
            queryString = queryString +
                    "      ?restaurant ontology_base:has_category ?has_category ." +
                    "      ?has_category ontology_base:name ?category ." +
                    "      ?has_address ontology_base:city ?name_city ." +
                    "      }";
        }
        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        // Output query results
        //ResultSetFormatter.out(System.out, results, query);

        while (results.hasNext()) {
            Restaurant rest = new Restaurant();
            QuerySolution sol = results.next();
            rest.setURI(sol.get("restaurant").toString());
            rest.setName(sol.get("name").toString());
            rest.setPhone_number(sol.get("phone_number").toString());
            rest.setCategory(sol.get("category").toString());
            rest.setStreet(sol.get("street").toString());
            rest.setCity_name(sol.get("name_city").toString());
            //System.out.println(person.toString());
            restaurants.add(rest);
        }


        // Important - free up resources used running the query
        qe.close();
        model.close();

        return restaurants;
    }

    public static Model createModel(String fileName){
        // create an empty model
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }
        // read the RDF/XML file
        model.read(in, "");

        // write it to standard out
        //model.write(System.out);
        return model;
    }

}
