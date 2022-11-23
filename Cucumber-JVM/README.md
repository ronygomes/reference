## Cucumber-JVM Maven

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

Maven `e` flag will show also Gherkin feature syntax error
```shell
$ ./mvnw test -e
```

Can run for for specific tag with following command:
```shell
$ ./mvnw test -Dcucumber.filter.tags="@slow and not @hook_enabled"
```