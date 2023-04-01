package ParserEngine;

import javax.xml.XMLConstants;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xquery.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import oracle.xml.xquery.OXQDataSource;

public class XMLQuerier {
    
    public XMLQuerier() throws Exception {}

    public NodeList SimpleQuery(Document document, String expression) throws Exception {
        XPath xPath = xFactory.newXPath();
        return (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
    }

    public XQResultSequence ComplexQuery(Document document, String query) throws Exception {
        OXQDataSource ds = new OXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression expr = conn.prepareExpression(query);
        return expr.executeQuery();
    }

    private XPathFactory xFactory = XPathFactory.newInstance();
}
