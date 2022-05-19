package me.ronygomes.reference.mockito;

public class PersonService {

    private NotificationService notificationService;

    public PersonService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    void createPerson(String name, int age) {
        Person p = new Person();
        p.setName(name);
        p.setAge(age);

        notificationService.notifyPerson(p);
    }
}
