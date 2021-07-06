## Java 12 Switch Expression (Preview)

`Java Switch Expression` was first introduced as `Preivew Featuer` in Java 12,
later this feature was finalized in Java 14. It addressed some limitation of 
switch statement and made it more modern and user friendly.

### Class Details

For demonstration I have following classes in this project:

[Shape.java][1] - Simple Enum with following memebrs: CIRCLE, TRIANGLE, RECTANGLE, TRAPEZIUM, RHOMBUS, PENTAGON, HEXAGON, UNKNOWN.

[SwitchDemo.java][2] - Interface with single method `int getSides(Shape shape)` which returns count of sides given a `Shape`.

[SwitchCaseSwitchDemo.java][3] - Implementation of `SwitchDemo` using old style switch case.

[SwitchExpressionSwitchDemo.java][4] - Implementation of `SwitchDemo` using new style switch expression.

[AbstractSwitchDemoTest.java][5] - Base test cases with implementation for switch case and switch expression.

### Run
Assuming Java 12 is installed, tests can be run using following command. This project doesn't have any `main(String[])` to run.

```java
$ ./gradlew test
```

For running from terminal `--enable-preview` need to be provided for both compile and runtime as
switch expression was `Preview Feature` in Java 12 and 13. [build.gradle][6] is configured to run in Java 12 as `Preview Feature`.

### Resource
* [JEP 12: Preview Features](https://openjdk.java.net/jeps/12)
* [JEP 325: Switch Expressions (Preview)](https://openjdk.java.net/jeps/325)
* [JEP 354: Switch Expressions (Second Preview)](https://openjdk.java.net/jeps/354)
* [JEP 361: Switch Expressions](https://openjdk.java.net/jeps/361)

[1]: https://github.com/ronygomes/reference/blob/master/Java12SwitchExpression/src/main/java/me/ronygomes/reference/Shape.java
[2]: https://github.com/ronygomes/reference/blob/master/Java12SwitchExpression/src/main/java/me/ronygomes/reference/SwitchDemo.java
[3]: https://github.com/ronygomes/reference/blob/master/Java12SwitchExpression/src/main/java/me/ronygomes/reference/SwitchCaseSwitchDemo.java
[4]: https://github.com/ronygomes/reference/blob/master/Java12SwitchExpression/src/main/java/me/ronygomes/reference/SwitchExpressionSwitchDemo.java
[5]: https://github.com/ronygomes/reference/blob/master/Java12SwitchExpression/src/test/java/me/ronygomes/reference/AbstractSwitchDemoTest.java
[6]: https://github.com/ronygomes/reference/blob/master/Java12SwitchExpression/build.gradle
