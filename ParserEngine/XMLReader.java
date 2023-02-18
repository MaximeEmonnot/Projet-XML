package ParserEngine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XMLReader {
    
    public XMLReader() throws Exception{
        dBuilder = dbFactory.newDocumentBuilder();
    }

    public Document OpenDocument(String filepath) throws Exception{

        Document output = null;
        Path path = Paths.get(filepath);
        if (Files.exists(path)){
            output = dBuilder.parse(new File(filepath));
            output.getDocumentElement().normalize();
        }
        else output = dBuilder.newDocument();
    
        return output;
    }
    
    private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder dBuilder;
}
