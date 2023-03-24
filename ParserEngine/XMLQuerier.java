package ParserEngine;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xquery.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import oracle.xml.xquery.OXQDataSource;

public class XMLQuerier {
    
    public XMLQuerier(){}

    public NodeList SimpleQuery(Document document, String expression) throws Exception {
       return (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
    }

    public XQResultSequence ComplexQuery(Document document, String query) throws Exception {
        OXQDataSource ds = new OXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression expr = conn.prepareExpression(query);
        return expr.executeQuery();
    }

    private static XPath xPath = XPathFactory.newInstance().newXPath();
}
