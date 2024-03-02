## Spring Security Digest Reference

Basic Authentication is required to send password in plain text. Digest authentication is an improvement over Basic authentication
where server sends a random key (nonce) and client sends md5_hash(nonce, username, realm, URI, password).

Server also generates the same MD5 hash and matches that client value. Digest authentication is unsecure as MD5 hash is
not secure enough.

As server does MD5 hash, so it requires access to password in plain text. For that reason need to store password in
plain text in server database.

Spring Security generates `nonch` using following algorithm:
```
base64(expirationTime + ":" + md5Hex(expirationTime + ":" + key))

expirationTime:   The date and time when the nonce expires, expressed in milliseconds
key:              A private key to prevent modification of the nonce token
```

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```

By default, a credential with name 'user' and random password is generated. Following configuration will create a static user/password

```
spring.security.user.name=admin
spring.security.user.password=12345
```

`curl` supports digest authentication with `--digest` flag:
```shell
$ curl --digest -u 'admin:12345' http://localhost:8080/greet
Hello World!
```

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Security, Spring Boot DevTools

### Reference
* https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/digest.html
* https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/basic.html
* https://stackoverflow.com/questions/75286514/
* https://datatracker.ietf.org/doc/html/rfc2617




