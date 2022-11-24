## Cucumber-JVM Maven

`Cucumber-JVM` is Behaviour Driven Development testing framework. It uses `Gherkin` analogous to plain text for writing
feature specification.

### Feature Details
I have following `features` written in this project:

[all_keyword.feature][1] - Used all Gherkin keyword
[cucumber_expression.feature][2] - For matching step definition expression, this is alternative to RegEx. 
[custom_parameter.feature][3] - Custom parameter can be defined to match in expression
[gotchas.feature][4] - Some unexpected behaviour
[hook.feature][5] - Analogous to JUnit @Before, @After methods
[lambda.feature][6] - Step function can be also defined using Lambda
[regex_expression.feature][7] - Older way of matching step definition. Obscure but more powerful
[special_input.feature][8] - Gherkin has an intuitive way of taking tabular or list data

### Run

Maven `e` flag will also show Gherkin feature syntax error (if any)
```shell
$ ./mvnw test -e
```

Can run for specific tag with following command:
```shell
$ ./mvnw test -Dcucumber.filter.tags="@slow and not @hook_enabled"
```

or can run using Maven Exec plugin:
```shell
$ ./mvnw exec:java -Dexec.classpathScope=test \
 -Dexec.mainClass=io.cucumber.core.cli.Main \
 -Dexec.args="./src/test/resources/features/"
```

### Note

This project was generated using following command:

```shell
# Source: https://cucumber.io/docs/guides/10-minute-tutorial

$ mvn archetype:generate \
-DarchetypeGroupId=io.cucumber \
-DarchetypeArtifactId=cucumber-archetype \
-DarchetypeVersion=7.5.0 \
-DgroupId=me.ronygomes.reference \
-DartifactId=cucumber-demo \
-Dpackage=me.ronygomes.reference.cucumberDemo \
-Dversion=1.0.0-SNAPSHOT \
-DinteractiveMode=false

$ mvn wrapper:wrapper
```

[1]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/all_keyword.feature
[2]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/cucumber_expression.feature
[3]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/custom_parameter.feature
[4]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/gotchas.feature
[5]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/hook.feature
[6]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/lambda.feature
[7]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/regex_expression.feature
[8]: https://github.com/ronygomes/reference/blob/master/Cucumber-JVM/src/test/resources/features/special_input.feature
