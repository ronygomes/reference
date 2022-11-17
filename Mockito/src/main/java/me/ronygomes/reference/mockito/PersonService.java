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

    public void doInBackgroud(int timeInMs, String name, int age) {
        new Thread(() -> {
            try {
                Thread.sleep(timeInMs);
                createPerson(name, age);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
