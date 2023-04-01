package ParserEngine;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xquery.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import oracle.xml.xquery.OXQDataSource;

/*
 * Classe helper d'exécution des requêtes XPath, et XQuery (en théorie)
 */
public class XMLQuerier {
    
    public XMLQuerier() throws Exception {}

    // Requête simple, avec XPath
    public NodeList SimpleQuery(Document document, String expression) throws Exception {
        XPath xPath = xFactory.newXPath();
        return (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
    }

    // Requête complexe, avec XQuery (en théorie)
    public XQResultSequence ComplexQuery(Document document, String query) throws Exception {
        OXQDataSource ds = new OXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression expr = conn.prepareExpression(query);
        return expr.executeQuery();
    }

    private XPathFactory xFactory = XPathFactory.newInstance();
}
