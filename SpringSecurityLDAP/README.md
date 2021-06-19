## Spring Security LDAP Example

Spring Security can authenticate using LDAP. In this project I learned to authenticate against embedded LDAP and
JumpCloud LDAP-as-Service.

#### Project Structure
A standard spring boot application containing 2 profiles named `embedded` and `jumpcloud`. By default it runs
`embedded` profile. To run in `jumpcloud` profile, need to set **spring.profiles.active** to `jumpcloud` in
`application.properties` file or provide as `--args` in gradle. `jumpcloud` profile expects following environment
variable need to be set with appropriate value.

Variable Name                  | Description
-------------------------------|-------------------------------------
**JUMPCLOUD_ORG_ID**           | Registered JumpCloud organization id
**JUMPCLOUD_MANAGER_PASSWORD** | Bind DN user's uid
**JUMPCLOUD_MANAGER_PASSWORD** | Bind DN user's password


#### Build and Run
Execute following commands to run the project (assuming $JAVA_HOME is configured).

```bash
$ cd reference/SpringSecurityLDAP
$ ./gradlew clean build bootRun --args='--spring.profiles.active=embedded'
```

#### UserDetailsContextMapper
Often time after authentication additional data (example: profile picture) need to be fetched from other sources like
RBDMS or Filesystem. Spring `UserDetailsContextMapper` provides a way to load additional details about authenticated
user. Although its not strictly require to authenticate using LDAP but provided here for completeness.
