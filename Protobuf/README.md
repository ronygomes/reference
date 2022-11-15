
```shell
$ apt install -y protobuf-compiler
$ protoc --version
libprotoc 3.6.1

$ mkdir java && mkdir python
$ protoc --java_out=java --python_out=python demo.proto

$ tree
.
├── java
│   └── DemoOuterClass.java
├── python
│   └── demo_pb2.py
└── demo.proto
```

```shell
JAVA_HOME=$(/usr/libexec/java_home -v11) ./gradlew clean build
```


```shell
$ cat person.proto 
syntax = 'proto3';

message Person {
    string name = 1;
    int32 age = 2;
}

$ cat person.txt 
name: "John"
age: 25

$ cat person.txt | protoc --encode=Person person.proto > person.bin

$ cat person.bin | protoc --decode=Person person.proto
name: "John"
age: 25
```