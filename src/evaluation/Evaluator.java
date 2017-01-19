package evaluation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.TreeMap;

/**
 *
 */
public class Evaluator {
	public static void evaluate(String goldStandardFilePath, String resultsFilePath) {    
		boolean isPerson2 = goldStandardFilePath.equals("./resources/data/person2/dataset21_dataset22_goldstandard_person.xml");
		TreeMap goldStandard = load(goldStandardFilePath,isPerson2);
		TreeMap results = load(resultsFilePath, isPerson2);

		int gs_size = goldStandard.size();
		int results_size = results.size();
		int correct = 0;
		//boolean bool = goldStandardFilePath.equals("./resources/data/person2/dataset21_dataset22_goldstandard_person.xml");
		
//		if (!bool) {
			for (Object r : goldStandard.keySet()) {
				String exactAns = (String) goldStandard.get(r);
				String predictedAns = "";

				if (results.get(r) != null) {
					predictedAns = (String) results.get(r);
				}

				if (exactAns.equals(predictedAns))
					correct++;
			}
//		}else{
//			for (Object r : goldStandard.keySet()) {
//				String exactAns = (String) r;
//				String predictedAns = "";
//				String correspondingEntity = (String) goldStandard.get(r);
//				if (correspondingEntity != null) {
//					predictedAns = (String) results.get(correspondingEntity);
//					if (predictedAns!= null && exactAns.equals(predictedAns))
//						correct++;
//				}
//			}
//		}
		System.out.println("size of results : "+ results_size);
		System.out.println("size of Gold Standard : "+ gs_size);
		float precision, recall, F1;
		precision = (float) correct / results_size * 100;
		recall = (float) correct/(gs_size) * 100;
		F1 = (float) 2 * precision * recall /(precision + recall);
		System.out.println("Precision: " + precision);
		System.out.println("Recall: " + recall);
		System.out.println("F1: " + F1);
	}

	static TreeMap load(String path, boolean isPerson2) {
		TreeMap hm = new TreeMap();
		try {
			File inputFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Cell");
			//System.out.println("----------------------------");
			int n = nList.getLength();// Person2 : 400
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//   System.out.println("entity : " + eElement.getAttribute("rdf:resource"));

				String entity1 = eElement
						.getElementsByTagName("entity1")
						.item(0).getAttributes().getNamedItem("rdf:resource").getTextContent();

				//System.out.println("entitt1: " + entity1);

				String entity2 =eElement
						.getElementsByTagName("entity2")
						.item(0).getAttributes()
						.getNamedItem("rdf:resource")
						.getTextContent();

				//System.out.println("entity2: " + entity2);
				//boolean bool = path.equals("./resources/data/person2/dataset21_dataset22_goldstandard_person.xml");
				if(isPerson2){
					hm.put(entity2, entity1);
				}else{
					hm.put(entity1, entity2);
				}

				//                }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int m = hm.size(); // Person2 : 95
		return hm;
	}
//	public static void main(String[] args) {
//		String resultFile = "./resources/data/person2/results.rdf";
//		String goldStandardPerson2FileName = "./resources/data/person2/dataset21_dataset22_goldstandard_person.xml";
//		Evaluator e = new Evaluator();
//		e.evaluate(goldStandardPerson2FileName, resultFile);
//	}
}
