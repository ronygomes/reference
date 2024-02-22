## Spring Security KeyCloak + JWT Reference

Following command will start development keycloak server at **http://localhost:8080**.

```shell
$ bin/kc.sh start-dev
```

### Imported Data Summary
Following data is exported in `ss-kc-realm.json`
* Realm: `ss-kc`
* Client: `webclient` with client role `admin` and `user`
* User: 
  * `app-admin` having client role `admin`
  * `user` having client role `user` 
  * `all-role` having role `admin, user`

### Command
```shell
# Start keycloak server using docker
$ docker run --name keycloak -p 8080:8080 \ 
  -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=12345 \
  quay.io/keycloak/keycloak start-dev

# Export Realm/User Configuration
$ docker exec keycloak /bin/sh \
    -c "/opt/keycloak/bin/kc.sh export --file /tmp/ss-kc-realm.json --realm=ss-kc"
$ docker exec keycloak cat /tmp/ss-kc-realm.json > ss-kc-realm.json

# Import Realm/User Configuration
$ docker run --name keycloak -p 8080:8080 \ 
  -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=12345 \
  -v ./ss-kc-realm.json:/opt/keycloak/data/import/ss-kc-realm.json:ro \
  quay.io/keycloak/keycloak start-dev --import-realm
```

Fetch JWT token using `curl`.
```shell
$ JWT_TOKEN=$(curl http://localhost:8080/realms/ss-kc/protocol/openid-connect/token \
-H 'Content-Type: application/x-www-form-urlencoded' \
-d 'client_id=webclient&grant_type=password&username=app-admin&password=12345')
```

Sample Token Response
```shell
echo $JWT_TOKEN | jq -r '.access_token' | cut -d '.' -f 2 | base64 -d
```
```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJPcXA3YlZVdHVxTUxYcmF2STNjU04zd29YdVBkOTJ3eDdzZEN5Sk1TcE9zIn0.eyJleHAiOjE3MDg1OTYxNzUsImlhdCI6MTcwODU5NTg3NSwianRpIjoiYzUzOTFiN2ItNDg2YS00MGI2LTg1MmYtNzBlNTRlMTU3MDgyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9zcy1rYyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJlMjlkMTk4Zi1jNmY0LTRiMmYtYjNlMC02ZTVjYjNlNjc3NDUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ3ZWJjbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiMWI5M2UxMWYtNTM2My00MDQ4LWIzYmUtOGU0NWNkODMwZWUyIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODEiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLXNzLWtjIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsid2ViY2xpZW50Ijp7InJvbGVzIjpbImFkbWluIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiIxYjkzZTExZi01MzYzLTQwNDgtYjNiZS04ZTQ1Y2Q4MzBlZTIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiYXBwLWFkbWluIn0.Val_I65X2dOS323AEd85p2KmWIYkqS9YBDLWlfWOC2Ekp6JDYmujMeU66OtnrU7i2frCY7hs9q86RqK8nvw7Hr1oAb1IxySJPptImBRPhYjd2ryHULEpF-U_ssJcQ1zkA7nweis5TTvVBbu_rRgG9aOP2Y377dpDGcuaF2xxslt0gf5rKFkxetdabQGk1OmGjiNLd_JxzFPMz3orM2XpIXX1AP_SB2yoe_8Cex4js-eriDih2dUBV5qp_Ulb7Au2T-dzovzzoZyBRPmKT_34hEm4I_02mHO9OEfylovUznsjQ_H07hqMpa4_6-IuUPjunMGJhgWtAmeeIXAsa0R3qA",
  "expires_in": 300,
  "refresh_expires_in": 1800,
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlZTllMTc5YS04YWZhLTRiOWMtYTNjMi1kOGM1ZGNlZDk3NTAifQ.eyJleHAiOjE3MDg1OTc2NzUsImlhdCI6MTcwODU5NTg3NSwianRpIjoiNDhmOGViYjEtNmI3ZS00MGM0LWJjMDItYTQ3N2U4NmI3ZjFlIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9zcy1rYyIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9yZWFsbXMvc3Mta2MiLCJzdWIiOiJlMjlkMTk4Zi1jNmY0LTRiMmYtYjNlMC02ZTVjYjNlNjc3NDUiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoid2ViY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6IjFiOTNlMTFmLTUzNjMtNDA0OC1iM2JlLThlNDVjZDgzMGVlMiIsInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjFiOTNlMTFmLTUzNjMtNDA0OC1iM2JlLThlNDVjZDgzMGVlMiJ9.zZzUmMfXwLcHzLtsuoXZzblJ0--kXWe6LMNl-TyLE_E",
  "token_type": "Bearer",
  "not-before-policy": 0,
  "session_state": "1b93e11f-5363-4048-b3be-8e45cd830ee2",
  "scope": "profile email"
}
```

Extract JWT Payload JSON.
```shell
$ echo $JWT_PAYLOAD | cut -d '.' -f 2 | tr -d '\n' | base64 -d
```
```json
{
  "exp": 1708596175,
  "iat": 1708595875,
  "jti": "c5391b7b-486a-40b6-852f-70e54e157082",
  "iss": "http://localhost:8080/realms/ss-kc",
  "aud": "account",
  "sub": "e29d198f-c6f4-4b2f-b3e0-6e5cb3e67745",
  "typ": "Bearer",
  "azp": "webclient",
  "session_state": "1b93e11f-5363-4048-b3be-8e45cd830ee2",
  "acr": "1",
  "allowed-origins": [
    "http://localhost:8081"
  ],
  "realm_access": {
    "roles": [
      "offline_access",
      "uma_authorization",
      "default-roles-ss-kc"
    ]
  },
  "resource_access": {
    "webclient": {
      "roles": [
        "admin"
      ]
    },
    "account": {
      "roles": [
        "manage-account",
        "manage-account-links",
        "view-profile"
      ]
    }
  },
  "scope": "profile email",
  "sid": "1b93e11f-5363-4048-b3be-8e45cd830ee2",
  "email_verified": true,
  "preferred_username": "app-admin"
}
```

### Reference

* https://stackoverflow.com/questions/76722008