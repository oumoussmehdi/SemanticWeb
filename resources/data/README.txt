Dataset Restaurant 
==================
The dataset contains:
restaurant1.rdf
restaurant2.rdf
ontology_restaurant1.owl
ontology_restaurant2.owl
restaurant1_restaurant2_goldstandard.rdf

restaurant1.rdf and restaurant2.rdf contain actual data. ontology_restaurant1.owl and ontology_restaurant2.owl are ontologies for restaurant1.rdf and restaurant2.rdf respectively. restaurant1_restaurant2_goldstandard.rdf is a golden startard of matching results from two data files.

The restaurant dataset is created with the help of 864 restaurant records from two different data sources (Fodor’s and Zagat’s restaurant guides) provided by Sheila Tejada, can be downloaded from http://userweb.cs.utexas.edu/users/ml/riddle/data.html. Restaurants are described by name, street, city, phone and restaurant category. Among these, 112 record pairs refer to the same entity present in different files restaurant1.rdf and restaurant2.rdf, but usually display certain differences.


Dataset person1
================
The dataset contains:
person11.rdf
person12.rdf
ontology_people1.owl
ontology_people2.owl
dataset11_dataset12_goldstandard_person.xml

person11.rdf and person12.rdf contain actual data. ontology_people1.owl and ontology_people2.owl are ontologies for person11.rdf and person12.rdf respectively. dataset11_dataset12_goldstandard_person.xml is a golden startard of matching results from two data files.

person1 dataset is created with the help of the Febrl project example datasets downloaded from http://sourceforge.net/projects/febrl/. It contains two files, person11.rdf with original records of people and person12.rdf with modified duplicate records of the entries from first file. The duplicate record file contains one duplicate per original record, maximum 1 modification per duplicate record and maximum 1 modification per attribute. 

Both files contain 500 records, with $A \cap B = 500$.

The attributes of the records are given name, surname, street number, address, suburb, postcode, state, date of birth, age, phone number, social security number.

Dataset person2
================
The dataset contains:
person21.rdf
person22.rdf
ontology_people1.owl
ontology_people2.owl
dataset21_dataset22_goldstandard_person.xml

person21.rdf and person22.rdf contain actual data. ontology_people1.owl and ontology_people2.owl are ontologies for person21.rdf and person22.rdf respectively. dataset21_dataset22_goldstandard_person.xml is a golden startard of matching results from two data files.

person2 dataset is created with the help of the Febrl project example datasets downloaded from http://sourceforge.net/projects/febrl/.
It contains two files, person21.rdf with original records of people and person22.rdf with modified records from the first file. person22.rdf contains maximum 9 modified entries for an original records in person21.rdf, with maximum 3 modifications per attribute, and maximum 10 modifications per record. 

person21.rdf contains 600 records, while person22.rdf contains 400 records which are modifications of 95 original records from person21.rdf. 

The attributes of the records are given name, surname, street number, address, suburb, postcode, state, date of birth, age, phone number, social security number.