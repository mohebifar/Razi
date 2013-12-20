/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.razi.data.elements;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import com.razi.models.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Mohamad
 */
public class ElementsLoader {

    private final static ArrayList<Element> elements = new ArrayList<>();
    private static boolean loaded = false;

    private static void load() {
        URL url = ElementsLoader.class.getResource("Elements.xml");
        File xml = new File(url.getPath());
        xml.toString();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            try {
                Document doc = dBuilder.parse(xml);
                NodeList nl = doc.getDocumentElement().getChildNodes();
                for (int i = 0; i < nl.getLength(); i++) {

                    try {
                        Node node = nl.item(i);
                        if (node instanceof org.w3c.dom.Element) {

                            org.w3c.dom.Element child = (org.w3c.dom.Element) node;
                            Element element = new Element();

                            element.setName(child.getElementsByTagName("Name").item(0).getTextContent());
                            element.setSymbol(child.getElementsByTagName("Symbol").item(0).getTextContent());

                            element.setAtomicNumber(Integer.parseInt(child.getElementsByTagName("AtomicNumber").item(0).getTextContent()));
                            elements.add(element);
                        }
                    } catch (Exception e) {
                    }

                }

            } catch (Exception e) {
            }

        } catch (ParserConfigurationException e) {
        }
    }

    public static Element getAtomBySymbol(String symbol) throws Exception {
        checkLoad();

        for (Element element : elements) {
            if (element.getSymbol().contains(symbol)) {
                return element;
            }
        }

        throw new Exception("There is no atom with given symbol");
    }

    public static boolean hasAtomBySymbol(String symbol) throws Exception {
        checkLoad();

        for (Element element : elements) {
            if (element.getSymbol().contains(symbol)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if xml file loaded or not
     * If it was not loaded, loads it
     */
    public static void checkLoad() {
        if (loaded != true) {
            load();
            loaded = true;
        }
    }
}
