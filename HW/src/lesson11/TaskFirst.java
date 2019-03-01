package lesson11;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class TaskFirst {
    static ArrayList<MyClass> application = new ArrayList<>();

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        URL url = new URL("https://goo.gl/tFpBDV");
        StringBuilder stringBuilder = new StringBuilder();
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             FileWriter write = new FileWriter("lesson11first.txt")) {
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }
            write.write(stringBuilder.toString().replaceAll("  ", ""));
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXPars saxp = new SAXPars();
        parser.parse(new File("lesson11first.txt"), saxp);
        for (MyClass myClass : application) {
            System.out.println(myClass.name);
            for (Method method : myClass.arrayList) {
                System.out.println("\t" + method.name);
                if (!method.value.isEmpty())
                    System.out.println("\t\t" + method.value);
            }
        }
        fromListToXML();
    }

    static void fromListToXML() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("data");
        document.appendChild(root);
        for (MyClass myClass : application) {
            Element clazz = document.createElement("class");
            root.appendChild(clazz);
            clazz.setAttribute("name", myClass.name);
            for (Method meth : myClass.arrayList) {
                Element method = document.createElement("method");
                clazz.appendChild(method);
                method.setAttribute("name", meth.name);
                if (!meth.value.isEmpty())
                    method.appendChild(document.createTextNode(meth.value));
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("lesson11first.xml"));
        transformer.transform(source, result);
    }

    public static class SAXPars extends DefaultHandler {
        String temp;
        String name;

        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("class")) {
                temp = attributes.getValue("name");
                application.add(new MyClass(temp));
            }
            if (qName.equals("method")) {
                name = attributes.getValue("name");
                for (MyClass myClass : application) {
                    if (myClass.name.equals(temp))
                        myClass.arrayList.add(new Method(name));
                }
            }
        }

        public void characters(char[] ch, int start, int length) {
            String information = new String(ch, start, length);
            if (!information.isEmpty()) {
                for (MyClass myClass : application) {
                    if (myClass.name.equals(temp)) {
                        for (Method method : myClass.arrayList) {
                            if (method.name.equals(name))
                                method.value = information;
                        }
                    }
                }
            }
        }
    }
}
