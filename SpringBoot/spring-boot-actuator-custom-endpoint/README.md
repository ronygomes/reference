## Spring Actuator Custom Endpoint Reference

* Only `health` endpoint is enabled be default. Enabled `health,info,env,version`
* Default actuator endpoint base path is `/actuator` but changed to `/management` here
* Default `/actuator/health` endpoint is remapped as `/management/health/status`
* Custom endpoint is `$BASE_PATH/version`, but remapped `$BASE_PATH/spring/version`
* `env` endpoint is configured to show sensitive information ADMIN users

Assuming SKDMan! is installed, run following command:
```shell
# Install Java 17, specified in .sdkmanrc
$ sdk env 
# Start Spring Boot Application
$ ./gradlew clean build bootRun

# Print Custom Endpoint
$ curl --silent --user user:12345 http://localhost:8080/management/spring/version | jq
{
  "versions": {
    "spring-core": "6.1.3",
    "spring-boot": "3.2.2",
    "spring-security-core": "6.2.1"
  }
}

# `admin` profile will give default user ADMIN roles
$ SPRING_PROFILES_ACTIVE=admin ./gradlew clean build bootRun

# Will show sensitive environment variables as ADMIN user
$ curl --silent --user user:12345 http://localhost:8080/management/env | jq
```