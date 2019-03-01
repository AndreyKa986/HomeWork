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

public class TaskSecond {
    static ArrayList<Man> humans = new ArrayList<>();

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        URL url = new URL("https://goo.gl/AZnd2V");
        StringBuilder stringBuilder = new StringBuilder();
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             FileWriter write = new FileWriter("lesson11second.txt")) {
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }
            write.write(stringBuilder.toString().replaceAll("  ", ""));
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXPars saxp = new SAXPars();
        parser.parse(new File("lesson11second.txt"), saxp);
        for (Man man : humans)
            System.out.println(man.toString());
        fromListToXML();
    }

    static void fromListToXML() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("persons");
        document.appendChild(root);
        for (Man man : humans) {
            if (man instanceof Professor) {
                Element professor = document.createElement("professor");
                root.appendChild(professor);
                professor.setAttribute("name", man.name);
                professor.setAttribute("experience", ((Professor) man).experience);
                professor.setAttribute("discipline", ((Professor) man).discipline);
            }
            if (man instanceof Student) {
                Element student = document.createElement("student");
                root.appendChild(student);
                student.setAttribute("name", man.name);
                student.setAttribute("course", ((Student) man).course);
                student.setAttribute("specialization", ((Student) man).specialization);
            }
            if (man instanceof Member) {
                Element member = document.createElement("member");
                root.appendChild(member);
                member.setAttribute("name", man.name);
                member.setAttribute("position", ((Member) man).position);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("lesson11second.xml"));
        transformer.transform(source, result);
    }

    public static class SAXPars extends DefaultHandler {
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("professor")) {
                String name = attributes.getValue("name");
                String experience = attributes.getValue("experience");
                String discipline = attributes.getValue("discipline");
                humans.add(new Professor(name, experience, discipline));
            }
            if (qName.equals("student")) {
                String name = attributes.getValue("name");
                String course = attributes.getValue("course");
                String specialization = attributes.getValue("specialization");
                humans.add(new Student(name, course, specialization));
            }
            if (qName.equals("member")) {
                String name = attributes.getValue("name");
                String position = attributes.getValue("position");
                humans.add(new Member(name, position));
            }
        }
    }
}
