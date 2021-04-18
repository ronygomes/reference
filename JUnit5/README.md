# JUnit 5 Reference

## Note
* By Default JUnit 5 engine only searches tests in classes matching following regex: `^.*Tests?$`\
**Matches:** ExampleTest, ExampleTests\
**Doesn't Matches:** TestExample, ExampleTest1 

* JUnit 5 allows following annotations in interface\
    @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, @RepeatedTest, @ParameterizedTest, @TestFactory,
    @TestTemplate, @ExtendWith, @Tag

# Running for CLI
For running JUnit from console download `junit-platform-console-standalone.jar` from 
[here](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone).
Run following command:
```shell script
$ java -jar junit-platform-console-standalone-1.7.1.jar \
       -cp junit5-demo-1.0-SNAPSHOT.jar --scan-classpath
```
For further options use `-h` 
