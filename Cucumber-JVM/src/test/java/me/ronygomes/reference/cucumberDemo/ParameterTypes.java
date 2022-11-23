package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java.DataTableType;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.sl.In;
import io.cucumber.java8.En;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/*
 Recommended Convention is to name file as ParameterTypes.java.
 But cucumber will pick them up from any file on the glue path.
 */
public class ParameterTypes implements En {

    /*
    io.cucumber.java8.En also provides following lambda methods

    DataTableType((Map<String, String> data) -> new Person(data.get("name"), Integer.parseInt(data.get("age")));
    DocStringType("xml", (String text) -> {});
    ParameterType("person", "Person\((.*), (\d+)\)", (String name, String age) -> new Person(name, age));
    */

    @DataTableType
    public Person personTable(Map<String, String> data) {
        return new Person(data.get("name"), Integer.parseInt(data.get("age")));
    }

    @DocStringType
    public Document xml(String text) {
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(text.getBytes()));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Invalid xml");
    }

    @ParameterType(name = "person", value = "Person\\((.*), (\\d+)\\)")
    public Person personEntry(String name, String age) {
        return new Person(name, Integer.parseInt(age));
    }
}
