/**
 * Created by Soumaya Haj Youssef on 09/01/2017.
 */
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import data.*;
import linking.LinkData;
import java.io.InputStream;
import java.util.List;
import java.util.HashMap;
import evaluation.*;

/** Main class for :
 * - read RDF XML from a file
 * - load data in Person and Restaurant Lists
 */

public class Main extends Object {


    static final String person11FileName = "./resources/data/person1/person11.rdf";
    static final String ontology1Prefix = "ontology_person1.owl#";
    static final String person12FileName = "./resources/data/person1/person12.rdf";
    static final String goldStandardPerson1FileName = "./resources/data/person1/dataset11_dataset12_goldstandard_person.xml";

    static final String person21FileName = "./resources/data/person2/person21.rdf";
    static final String person22FileName = "./resources/data/person2/person22.rdf";
    static final String ontology2Prefix = "ontology_person2.owl#";
    static final String goldStandardPerson2FileName = "./resources/data/person2/dataset21_dataset22_goldstandard_person.xml";

    static final String restaurant1FileName = "./resources/data/restaurants/restaurant1.rdf";
    static final String restaurant2FileName = "./resources/data/restaurants/restaurant2.rdf";
    static final String ontologyRest1Prefix = "ontology_restaurant1.owl#";
    static final String ontologyRest2Prefix = "ontology_restaurant2.owl#";
    static final String goldStandardRestaurantFileName = "./resources/data/restaurants/restaurant1_restaurant2_goldstandard.rdf";
    
    static HashMap mapping = new HashMap();

    public static void main(String args[]) {
    
    	/* choose one of the three data sets:
    	 * 1. person1
    	 * 2. person2
    	 * 3. restaurants
    	 */
    	
        args = new String[] {"restaurants"};
        String dataType = args[0];

        if (dataType.equals("person1")){
            List<Person> persons11 = DataManipulation.readPersonRdfFile(person11FileName,ontology1Prefix);
            List<Person> persons12 = DataManipulation.readPersonRdfFile(person12FileName,ontology2Prefix);
            //check for similiraties between persons11 and persons12
            mapping = LinkData.findSimiliraties(persons11,persons12,dataType);
        }else if (dataType.equals("person2")){
            List<Person> persons21 = DataManipulation.readPersonRdfFile(person21FileName,ontology1Prefix);
            List<Person> persons22 = DataManipulation.readPersonRdfFile(person22FileName,ontology2Prefix);
            //check for similiraties between persons21 and persons22
            mapping = LinkData.findSimiliraties(persons21,persons22,dataType);
        }else if (dataType.equals("restaurants")){
            List<Restaurant> restaurants1 = DataManipulation.readRestaurantRdfFile(restaurant1FileName,ontologyRest1Prefix);
            List<Restaurant> restaurants2 = DataManipulation.readRestaurantRdfFile(restaurant2FileName,ontologyRest2Prefix);
            //check for similiraties between restaurant1 and restaurant2
            mapping = LinkData.findSimiliraties(restaurants1,restaurants2,dataType);
        }else{
            throw new RuntimeException("The program can accept only : \"person1\" or \"person2\" or \"restaurants\" as argument");

        }

        //write results in XML file
        String resultFile = "./resources/data/"+dataType+"/results.rdf";
        WriteXmlFile w = new WriteXmlFile(dataType, mapping);
        w.write(resultFile);

        //Evaluate results
        Evaluator e = new Evaluator();
        if (dataType.equals("person1")){
            e.evaluate(goldStandardPerson1FileName, resultFile);
        }else if (dataType.equals("person2")){
            e.evaluate(goldStandardPerson2FileName, resultFile);
        }else if (dataType.equals("restaurants")){
            e.evaluate(goldStandardRestaurantFileName, resultFile);
        }
    }


}


