package ParserEngine;

import java.io.File;
import java.io.StringReader;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

class XMLWriter{
    
    public XMLWriter() throws Exception{
    	// Utilise le xsl prettyprint pour indenter correctement le resultat
    	transformer = tFactory.newTransformer(new StreamSource(new File("./Assets/XML/prettyprint.xsl")));
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    }

    public void WriteDocumentToFile(Document document, String path) throws Exception{
        DOMSource source = new DOMSource(document);
        StreamResult output = new StreamResult(new File(path));
        transformer.transform(source, output);
    }

    private static final TransformerFactory tFactory = TransformerFactory.newInstance();
    private Transformer transformer;
}