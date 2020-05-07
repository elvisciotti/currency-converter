package io.github.elvisciotti.CurrencyConverter.Ecb;

import io.github.elvisciotti.CurrencyConverter.Ecb.Exception.WrongOnlineEcbXmlFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EcbRatesXmlFactory {

    public static EcbRates factory(String xmlContent) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException, WrongOnlineEcbXmlFormat {

        EcbRates ecbRates = new EcbRates();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));

        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile("//Cube/Cube/Cube")
                .evaluate(doc, XPathConstants.NODESET);

        int nodeListLength = nodeList.getLength();
        if (nodeListLength < 2) {
            throw new WrongOnlineEcbXmlFormat("European Central Bank XML unexpected format error");
        }
        for (int i = 0; i < nodeListLength; i++) {
            Element e = (Element) nodeList.item(i);
            String currency = e.getAttribute("currency");
            String rate = e.getAttribute("rate");
            ecbRates.add(currency, Float.valueOf(rate));
        }

        return ecbRates;
    }
}
