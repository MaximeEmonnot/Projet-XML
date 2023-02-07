package ParserEngine;
import org.w3c.dom.*;

import ExceptionEngine.EngineException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class XMLParser {

    public enum EType{
        Reader,
        Editor,
        Writer
    }

    public XMLParser(String path, EType _type) throws Exception{
        type = _type;
        switch(type){
            case Editor:
            case Reader:
             {
                File inputFile = new File(path);
                dBuilder = dbFactory.newDocumentBuilder();
                document = dBuilder.parse(inputFile);
                document.getDocumentElement().normalize();
             }
                break;
            case Writer:
            {
                dBuilder = dbFactory.newDocumentBuilder();
                document = dBuilder.newDocument();
            }
                break;
            default:
                break;
        }
    }

    public Element GetRootElement() {
        return document.getDocumentElement();
    }

    public NodeList GetElementsByName(String name){
        return document.getElementsByTagName(name);
    }

    public NodeList GetChildren() {
        return document.getChildNodes();
    }

    public Element CreateRoot(String name){
        return document.createElement(name);
    }
    
    public Attr CreateAttribute(String name){
        return document.createAttribute(name);
    }

    public void WriteToFile(String path) throws Exception{
        if (type != EType.Reader){
            transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult output = new StreamResult(new File(path));
            transformer.transform(source, output);
        }
        else throw new EngineException("You can't write in this mode! Please change to Editor or Writer");
    }

    private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder dBuilder;
    private static final TransformerFactory tFactory = TransformerFactory.newInstance();
    private Transformer transformer;
    private Document document;
    private EType type;
}
