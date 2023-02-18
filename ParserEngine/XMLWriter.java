package ParserEngine;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

class XMLWriter{
    
    public XMLWriter() throws Exception{
        transformer = tFactory.newTransformer();
    }

    public void WriteDocumentToFile(Document document, String path) throws Exception{
        DOMSource source = new DOMSource(document);
        StreamResult output = new StreamResult(new File(path));
        transformer.transform(source, output);
    }

    private static final TransformerFactory tFactory = TransformerFactory.newInstance();
    private Transformer transformer;
}