package ParserEngine;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/*
 * Classe helper pour la transformation XSLT d'un fichier XML
 */
public class XMLTransformer {
    
    public XMLTransformer() {}

    // Transformation à partir d'un document XML
    public void TransformDocument(Document document, String xsltPath, String path) throws Exception {
        Source xslt = new StreamSource(new File(xsltPath));
        Transformer transformer = tFactory.newTransformer(xslt);

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
    }

    // Transformation à partir d'un Node
    public void TransformNode(Node node, String xsltPath, String path) throws Exception {
        Source xslt = new StreamSource(new File(xsltPath));
        Transformer transformer = tFactory.newTransformer(xslt);

        DOMSource source = new DOMSource(node);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
    }

    private static TransformerFactory tFactory = TransformerFactory.newInstance();
}
