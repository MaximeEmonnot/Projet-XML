package ParserEngine;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLQuerier {
    
    public XMLQuerier(){}

    public NodeList SimpleQuery(Document document, String expression) throws Exception {
       return (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
    }

    private static XPath xPath = XPathFactory.newInstance().newXPath();
}
