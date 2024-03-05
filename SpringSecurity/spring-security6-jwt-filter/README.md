## Spring Security 6 JWT Authentication Filter Reference

This project demonstrated custom `JwtAuthenticationFilter` filter which extracts JWT
token for creating Authentication object. User data is stored in H2 memory database.

Assuming Java 17 is installed, run following command to run the project:

```shell
$ ./gradlew clean build bootRun
```

Example of API invocation using `curl`:

```shell

# Access public pages without token
$ curl http://localhost:8080/api/public/message
{"message":"Welcome to Public Page!"}

# Authenticate with existing user
$ curl -d '{"email":"john@doe.com","password":"12345"}' \
       -H "Content-Type: application/json" \ 
       http://localhost:8080/api/user/authenticate

# Note: Example accessToken is for demonstration only
{
  "accessToken":"ayNiJ9.eyJzTczNTU3fQ.DFD-2KtSRxDGXctn-LJOIJ34",
  "tokenType":"Bearer"
}

# Fetch admin role message
$ curl -H "Authorization: Bearer ayNiJ9.eyJzTczNTU3fQ.DFD-2KtSRxDGXctn-LJOIJ34" \
        http://localhost:8080/api/admin/message

# Register an User        
$ curl -d '{"firstName":"Jane","lastName":"Doe","email":"jane@doe.com","password":"12345","role":"USER"}' \
       -H "Content-Type: application/json" \ 
       http://localhost:8080/api/user/register
```
