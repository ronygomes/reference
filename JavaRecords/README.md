## Java Records

`Java Records` was first introduced as `Preivew Featuer` in Java 13,
later this feature was finalized in Java 15. It allows creating classes 
that act as transparent carriers for immutable data.

### Class Details

For demonstration, I have the following classes/records in this project:

[Person.java][1] - Main record class which implements `Validator` interface

[Person$EmailParts.java][2] - Class used for local record demonstration

[Validator.java][3] - Interface with single method `boolean isValid()` for demonstrating interface use case with record

[RecordTest.java][4] - Unit test of `Person` for validating behavior

### Run
Assuming Java 15 is installed, tests can be run using following command. This project doesn't have any `main(String[])` to run.

```java
$ ./gradlew test
```

### Resource
* [JEP 359: Records (Preview)](https://openjdk.java.net/jeps/359)
* [JEP 384: Records (Second Preview)](https://openjdk.java.net/jeps/384)
* [JEP 395: Records](https://openjdk.java.net/jeps/395)
* [JEP 12: Preview Features](https://openjdk.java.net/jeps/12)

[1]: https://github.com/ronygomes/reference/blob/master/JavaRecords/src/main/java/me/ronygomes/reference/Person.java
[2]: https://github.com/ronygomes/reference/blob/master/JavaRecords/src/main/java/me/ronygomes/reference/Person.java#L20-L24
[3]: https://github.com/ronygomes/reference/blob/master/JavaRecords/src/main/java/me/ronygomes/reference/Validator.java
[4]: https://github.com/ronygomes/reference/blob/master/JavaRecords/src/test/java/me/ronygomes/reference/RecordTest.java
