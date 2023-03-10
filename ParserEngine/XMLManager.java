package ParserEngine;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLManager {
    
    private XMLManager() throws Exception {
        xmlReader = new XMLReader();
        xmlWriter = new XMLWriter();
        xmlQuerier = new XMLQuerier();
        xmlTransformer = new XMLTransformer();
    }

    public synchronized static XMLManager GetInstance() throws Exception {
        if (instance == null)
            instance = new XMLManager();
        return instance;
    }

    public Document GetDocument(String path) throws Exception {
        if (!documents.containsKey(path)) 
            documents.put(path, xmlReader.OpenDocument(path));
        return documents.get(path);
    }

    public void WriteToFile(String path) throws Exception {
        WriteToFile(GetDocument(path), path);
    }
    public void WriteToFile(Document document, String path) throws Exception {
        xmlWriter.WriteDocumentToFile(document, path);
    }

    public NodeList SimpleQuery(String path, String expression) throws Exception {
        return SimpleQuery(GetDocument(path), expression);
    }
    public NodeList SimpleQuery(Document document, String expression) throws Exception {
        return xmlQuerier.SimpleQuery(document, expression);
    }

    public void TransformDocument(String documentPath, String xsltPath, String outputPath) throws Exception{
        TransformDocument(GetDocument(documentPath), xsltPath, outputPath);
    }
    public void TransformDocument(Document document, String xsltPath, String outputPath) throws Exception {
        xmlTransformer.TransformDocument(document, xsltPath, outputPath);
    }

    private static XMLManager instance = null;

    private Map<String, Document> documents = new HashMap<String, Document>();

    private XMLReader      xmlReader;
    private XMLWriter      xmlWriter;
    private XMLQuerier     xmlQuerier;
    private XMLTransformer xmlTransformer;
}
