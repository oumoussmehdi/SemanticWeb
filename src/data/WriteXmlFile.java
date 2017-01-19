package data;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * write the results in a mappings to an rdf file
 *
 */
public class WriteXmlFile {
    private HashMap mappings;
    private String dataSet;
    public WriteXmlFile(String dataSet, HashMap mappings){
        this.mappings=mappings;
        this.dataSet = dataSet;
    }
    public void write(String outputPath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("rdf:RDF");//

            doc.appendChild(rootElement);
            Attr attr = doc.createAttribute("xmlns");
            attr.setValue("http://knowledgeweb.semanticweb.org/heterogeneity/alignment#");
            rootElement.setAttributeNode(attr);
            Attr attr_rdf = doc.createAttribute("xmlns:rdf");
            attr_rdf.setValue("http://www.w3.org/1999/02/22-rdf-syntax-ns#");
            rootElement.setAttributeNode(attr_rdf);
            Attr attr_xsd = doc.createAttribute("xmlns:xsd");
            attr_xsd.setValue("http://www.w3.org/1999/02/22-rdf-syntax-ns#");
            rootElement.setAttributeNode(attr_xsd);
            Attr attr_align = doc.createAttribute("xmlns:align");
            attr_align.setValue("http://knowledgeweb.semanticweb.org/heterogeneity/alignment#");
            rootElement.setAttributeNode(attr_align);

            // staff elements
            Element alignment = doc.createElement("Alignment");
            rootElement.appendChild(alignment);

            // set attribute to staff element


            // shorten way
            // staff.setAttribute("id", "1");

            // xml element
            Element xml = doc.createElement("xml");
            xml.appendChild(doc.createTextNode("yes"));
            alignment.appendChild(xml);

            // level element
            Element level = doc.createElement("level");
            level.appendChild(doc.createTextNode("0"));
            alignment.appendChild(level);

            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode("**"));
            alignment.appendChild(type);

            // onto1 element
            Element onto1 = doc.createElement("onto1");
            onto1.appendChild(doc.createTextNode("null"));
            alignment.appendChild(onto1);

            // onto2 element
            Element onto2 = doc.createElement("onto2");
            onto2.appendChild(doc.createTextNode("null"));
            alignment.appendChild(onto2);


            for(Object m : mappings.keySet()){
            	Object o1;
                Object o2;
            	if(dataSet.equals("person2")){
            		o1 = mappings.get(m);
                    o2 = m;
            	}else{
            		o1 = m;
                    o2 = mappings.get(m);
            	}
                

                Element map = doc.createElement("map");
                alignment.appendChild(map);

                Element cell = doc.createElement("Cell");
                map.appendChild(cell);

                Element entity1 = doc.createElement("entity1");
                Attr attr1 = doc.createAttribute("rdf:resource");
                attr1.setValue(o1.toString());
                entity1.setAttributeNode(attr1);
                cell.appendChild(entity1);

                Element entity2 = doc.createElement("entity2");
                Attr attr2 = doc.createAttribute("rdf:resource");
                attr2.setValue(o2.toString());
                entity2.setAttributeNode(attr2);
                cell.appendChild(entity2);

                Element relation = doc.createElement("relation");
                relation.appendChild(doc.createTextNode("="));
                cell.appendChild(relation);

                Element measure = doc.createElement("measure");
                // this should be changed after
                measure.appendChild(doc.createTextNode("1.0"));
                cell.appendChild(measure);

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputPath));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}



