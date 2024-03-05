## Spring Security 6 LDAP Embedded Reference

Coded from Spring Security 6 LDAP reference. Allows Basic authentication using Embedded
LDAP unboundid.

**Note:** Wasn't able to run reference provided embedded LDAP file **users.ldif** directly.
Renamed attribute to **member** from **uniqueMember**.

If Java 17 is installed running following command will start the application:
```shell
$ ./gradlew clean build bootRun

$ curl -u user:password http://localhost:8080/greet
Hello World!
```

### Reference
* https://spring.io/guides/gs/authenticating-ldap/
* https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/ldap.html
