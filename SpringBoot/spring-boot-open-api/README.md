## Spring Boot OpenAPI v3 Reference

It is essential for REST API to have proper documentation, as it is intended to be consumed by
End Users. REST API must have documetation for each resource, endpoint and request/response
structure with constrains and validation.

It is cumbersome to write documentation manually. Spring Doc can auto generate API documentation 
as `OpenAPI specification` which is derived from Swagger specification. 

Another usefull tool is `Swagger UI` which providers UI for experimenting with the 
generated documetation.

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Validation, Spring Boot DevTools

Assuing JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

Only adding following dependency is enough for enabling endpoints for documentation:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.0</version>
    <scope>runtime</scope>
    <scope>
</dependency>
```

* Swagger UI: http://localhost:8080/swagger-ui.html
* OpenAPI v3 Documentation: http://localhost:8080/v3/api-docs

Following JSON is the generated for TaskResource.
```json
{
   "openapi":"3.0.1",
   "info":{
      "title":"OpenAPI definition",
      "version":"v0"
   },
   "servers":[
      {
         "url":"http://localhost:8080",
         "description":"Generated server url"
      }
   ],
   "paths":{
      "/tasks":{
         "get":{
            "tags":[
               "task-resource"
            ],
            "operationId":"retrieveAllTasks",
            "responses":{
               "200":{
                  "description":"OK",
                  "content":{
                     "*/*":{
                        "schema":{
                           "type":"array",
                           "items":{
                              "$ref":"#/components/schemas/Task"
                           }
                        }
                     }
                  }
               }
            }
         },
         "post":{
            "tags":[
               "task-resource"
            ],
            "operationId":"createUser",
            "requestBody":{
               "content":{
                  "application/json":{
                     "schema":{
                        "$ref":"#/components/schemas/Task"
                     }
                  }
               },
               "required":true
            },
            "responses":{
               "200":{
                  "description":"OK",
                  "content":{
                     "*/*":{
                        "schema":{
                           "$ref":"#/components/schemas/Task"
                        }
                     }
                  }
               }
            }
         }
      },
      "/tasks/{id}":{
         "get":{
            "tags":[
               "task-resource"
            ],
            "operationId":"retrieveTask",
            "parameters":[
               {
                  "name":"id",
                  "in":"path",
                  "required":true,
                  "schema":{
                     "type":"integer",
                     "format":"int32"
                  }
               }
            ],
            "responses":{
               "200":{
                  "description":"OK",
                  "content":{
                     "*/*":{
                        "schema":{
                           "$ref":"#/components/schemas/Task"
                        }
                     }
                  }
               }
            }
         },
         "delete":{
            "tags":[
               "task-resource"
            ],
            "operationId":"deleteUser",
            "parameters":[
               {
                  "name":"id",
                  "in":"path",
                  "required":true,
                  "schema":{
                     "type":"integer",
                     "format":"int32"
                  }
               }
            ],
            "responses":{
               "200":{
                  "description":"OK"
               }
            }
         }
      }
   },
   "components":{
      "schemas":{
         "Task":{
            "type":"object",
            "properties":{
               "id":{
                  "type":"integer",
                  "format":"int32"
               },
               "task":{
                  "maxLength":10,
                  "minLength":2,
                  "type":"string"
               },
               "dueDate":{
                  "type":"string",
                  "format":"date"
               }
            }
         }
      }
   }
}
```