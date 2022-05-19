package me.ronygomes.reference.mockito;

public class NotificationService {

    public void notifyPerson(Person person) {
        System.out.printf("Notified %s (%d)\n", person.getName(), person.getAge());
    }
}
