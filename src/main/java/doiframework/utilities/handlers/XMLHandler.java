package doiframework.utilities.handlers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class XMLHandler {
    public void handle(String filepath){
        try {
            File file = new File(filepath);
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();
            Document document = docBuilder.parse(file);
            document.getDocumentElement().normalize();
        }
        catch (SAXException | ParserConfigurationException | IOException e){
            System.err.println("exception" + e);
        }
    }
}
