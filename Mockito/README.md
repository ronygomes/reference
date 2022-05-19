## Mockito Reference

`Mockito` is the coolest mocking library for Java. Here learned and 
played with almost all the APIs of the library.

### Class Details

I have these Unit Test classes in this project:

[ArgumentCaptorTest.java][1] - Clever way to capture arguments!
 
[ArgumentMatcherTest.java][2] - Played with argument matchers like `any()`, `isNull()`, `startWith()` and more

[BDDStyleTest.java][3] - Learned about Mockito BDD alias for `when()` and `thenReturn()`

[MockitoAnnotationTest.java][4] - Setup mockito for `@Mock` and other annotations
 
[MockitoJUnitExtensionTest.java][5] - Setup mockito for `@Mock` and other annotations with JUnit Extension

[ReturnValueStubbingTest.java][6] - Almost all possible way to stub methods

[SpyStubbingTest.java][7] - Learned difference between `spy()` and `mock()`

[VerificationTest.java][8] - The reason for mock - `verify()`

### Run
Assuming Java 8 is installed, tests can be run using following command. This project doesn't have any `main(String[])` to run.

```java
$ ./gradlew test
```

### Resource
* [Mockito Framework Site](https://site.mockito.org/)
* [Mockito GitHub](https://github.com/mockito/mockito)

[1]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/ArgumentCaptorTest.java
[2]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/ArgumentMatcherTest.java
[3]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/BDDStyleTest.java
[4]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/MockitoAnnotationTest.java
[5]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/MockitoJUnitExtensionTest.java
[6]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/ReturnValueStubbingTest.java
[7]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/SpyStubbingTest.java
[8]: https://github.com/ronygomes/reference/blob/master/Mockito/src/test/java/me/ronygomes/reference/mockito/VerificationTest.java
