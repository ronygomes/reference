## Java 11 Nest-Based Access Control

Java 11 does not creates public access methods for nested class anymore. Now a nest
is created with all the classes. Java 11 also provided API for querying nest related
information.

### Class Details

[SolarSystem.java](https://github.com/ronygomes/reference/blob/master/Java11NestBasedAccess/src/main/java/me/ronygomes/reference/nest_access/SolarSystem.java) - `SolarSystem` class has 3 innte class `Earth`, `Uranus`, `Saturn`.
`Earth` class has 2 inner class `Asia` and `Europe`.

[SolarSystemTest.java](https://github.com/ronygomes/reference/blob/master/Java11NestBasedAccess/src/test/java/me/ronygomes/reference/nest_access/SolarSystemTest.java) - Experiment with Nest-Based Access API

### Resource

* [JEP 181: Nest-Based Access Control](https://openjdk.java.net/jeps/181)
* [Java Nest API Docs](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Class.html#getNestHost())