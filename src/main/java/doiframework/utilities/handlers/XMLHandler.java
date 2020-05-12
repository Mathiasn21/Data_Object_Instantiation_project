package doiframework.utilities.handlers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class XMLHandler {
    public Document document;
    public void handle(String filepath, String elementToRetrieve){
        readDocument(filepath);
        NodeList list = document.getElementsByTagName(elementToRetrieve);
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            getNodeName(node);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element e = (Element) node;
                System.out.println(e.getChildNodes());
            }
        }
    }
    public void readDocument(String filepath){
        try {
            File file = new File(filepath);
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();
            document = docBuilder.parse(file);
            document.getDocumentElement().normalize();
        }
        catch (SAXException | ParserConfigurationException | IOException e){
            System.err.println("exception" + e);
        }
    }
    public String getRootElement(){
        return document.getDocumentElement().getNodeName();
    }
    public String getNodeName(Node node){
        return node.getNodeName();
    }
}
