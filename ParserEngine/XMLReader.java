package ParserEngine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/*
 * Classe helper pour la lecture d'un document XML
 */
public class XMLReader {
    
    public XMLReader() throws Exception{
        dbFactory.setIgnoringElementContentWhitespace(true);
        dbFactory.setIgnoringComments(true);
        dBuilder = dbFactory.newDocumentBuilder();
    }

    // Ouverture d'un fichier XML
    // Si un fichier n'existe pas, un nouveau fichier est créé au chemin renseigné
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
