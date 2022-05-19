package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ArgumentCaptorTest {

    @Test
    void testArgumentCaptor() {
        NotificationService notificationService = Mockito.mock(NotificationService.class);
        PersonService personService = new PersonService(notificationService);

        personService.createPerson("John", 25);
        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);

        Mockito.verify(notificationService).notifyPerson(argumentCaptor.capture());

        Person p = argumentCaptor.getValue();
        Assertions.assertEquals("John", p.getName());
        Assertions.assertEquals(25, p.getAge());
    }
}
