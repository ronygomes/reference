## Java 6 Service Loader Example

Java 6 Provides a way to automatically find the implementation of a spec from class path. All modern java specs like
JDBC, Bean Validation loads provider in this way.

#### Project Structure
This repository contains following Java projects 

- `greeter-api` - Service Spec
- `greeter-impl` - Service Provider/Implementation
- `greeter-client` - Service Consumer

#### Build and Run
Run following command for running the project.

```bash
$ cd greetre-client
$ gradle clean build run
```

#### Output

```
> Task :run
me.ronygomes.greeter.HelloGreeter - Hello World!
```
