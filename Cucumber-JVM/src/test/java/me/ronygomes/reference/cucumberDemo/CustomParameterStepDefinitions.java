package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Document;

import java.util.List;

public class CustomParameterStepDefinitions {

    // Trigger ParameterTypes.java#@ParameterType annotated method
    @Given("some person info as {person}")
    public void customObject(Person p) {
        Assertions.assertEquals("John", p.getName());
        Assertions.assertEquals(25, p.getAge());
    }

    // Trigger ParameterTypes.java#@DataTableType annotated method
    @Given("person info as table")
    public void personInfoAsTable(List<Person> persons) {
        Assertions.assertEquals(1, persons.size());
        Assertions.assertEquals("John", persons.get(0).getName());
        Assertions.assertEquals(25, persons.get(0).getAge());
    }


    // Automatically trigger ParameterTypes.java#@DocStringType
    @Given("person info as xml")
    public void personInfoAsXml(Document document) {
        Assertions.assertEquals("John", document.getElementsByTagName("name").item(0).getTextContent());
        Assertions.assertEquals("25", document.getElementsByTagName("age").item(0).getTextContent());
    }
}
