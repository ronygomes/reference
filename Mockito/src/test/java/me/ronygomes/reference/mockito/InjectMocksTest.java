package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * InjectMocks doesn't work with constructor injection with same type
 * Source: https://stackoverflow.com/questions/63889701/injectmocks-is-wrongly-injecting-the-same-mock-into-2-different-fields-of-simila
 */
@ExtendWith(MockitoExtension.class)
public class InjectMocksTest {

    @Mock
    private NotificationService notificationService;

    // NotificationService will be automatically injected in PersonService
    // Great for field injection without setter
    @InjectMocks
    private PersonService personService;

    @AfterEach
    void tearDown() {
        Mockito.reset(notificationService);
    }

    @Test
    void testInjectMocks() {
        personService.createPerson("John", 25);
        Mockito.verify(notificationService).notifyPerson(Mockito.any());
    }
}
