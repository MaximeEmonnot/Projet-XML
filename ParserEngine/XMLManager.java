package ParserEngine;

import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/* 
 * Singleton responsable de la gestion des fichiers XML
 * Rappelle les différentes méthodes des classes helper
 */
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

    // Ouverture d'un fichier XML
    public Document GetDocument(String path) throws Exception {
        return xmlReader.OpenDocument(path);
    }

    // Ecriture dans un fichier XMl
    public void WriteToFile(Document document, String path) throws Exception {
        xmlWriter.WriteDocumentToFile(document, path);
    }

    // Requête XPath dans un fichier XML
    public NodeList SimpleQuery(String path, String expression) throws Exception {
        return SimpleQuery(GetDocument(path), expression);
    }
    public NodeList SimpleQuery(Document document, String expression) throws Exception {
        return xmlQuerier.SimpleQuery(document, expression);
    }

    // Requête XQuery dans un fichier XML
    public XQResultSequence ComplexQuery(String path, String expression) throws Exception {
        return ComplexQuery(GetDocument(path), expression);
    }
    public XQResultSequence ComplexQuery(Document document, String expression) throws Exception {
        return xmlQuerier.ComplexQuery(document, expression);
    }

    // Transformation XSLT à partir d'un document XML
    public void TransformDocument(String documentPath, String xsltPath, String outputPath) throws Exception{
        TransformDocument(GetDocument(documentPath), xsltPath, outputPath);
    }
    public void TransformDocument(Document document, String xsltPath, String outputPath) throws Exception {
        xmlTransformer.TransformDocument(document, xsltPath, outputPath);
    }

    // Transformation XSLT à partir d'un Node
    public void TransformNode(Node node, String xsltPath, String outputPath) throws Exception {
        xmlTransformer.TransformNode(node, xsltPath, outputPath);
    }

    private static XMLManager instance = null; 

    private XMLReader      xmlReader;
    private XMLWriter      xmlWriter;
    private XMLQuerier     xmlQuerier;
    private XMLTransformer xmlTransformer;
}
