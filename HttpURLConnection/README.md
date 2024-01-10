## HttpURLConnection Example

Provides reference for creating REST API client using HttpURLConnection. This project doesn't have
any backend of its own. Uses `https://jsonplaceholder.typicode.com` public API's `comments` and `users`
resources.

**NOTE:** This project invokes REST endpoints directly in test, so internet connecting is required for passing the tests.

### Class Details

I have these Service and Unit Test classes in this project:

[CommentService.java][1] - Example of GET-ing List using HttpURLConnection

[UserService.java][2] - POST and single result GET example

[CommentServiceTest.java][3] - Test for CommentService.java

[UserServiceTest.java][4] - Test for UserServiceTest.java

#### Build and Run
This project doesn't have any `main()`, contains only tests. Run following command to execute test. 

```bash
$ cd HttpURLConnection
$ ./mvnw test
```

[1]: https://github.com/ronygomes/reference/blob/main/HttpURLConnection/src/main/java/me/ronygomes/reference/rest_huc/service/CommentService.java
[2]: https://github.com/ronygomes/reference/blob/main/HttpURLConnection/src/main/java/me/ronygomes/reference/rest_huc/service/UserService.java
[3]: https://github.com/ronygomes/reference/blob/main/HttpURLConnection/src/test/java/me/ronygomes/reference/rest_huc/service/CommentServiceTest.java
[4]: https://github.com/ronygomes/reference/blob/main/HttpURLConnection/src/test/java/me/ronygomes/reference/rest_huc/service/UserServiceTest.java

