## Protocol Buffers Reference

### Class Details

I have these Unit Test classes in this project:

[AllTypeOuterClassTest][1] - All the supported types and their default value
 
[BinaryStoreTest.java][2] - Read and write from protocol buffers byte data

[ImporterTest.java][3] - Import other custom protocol buffer file

[JsonConversionTest.java][4] - Serialize and Deserialize to JSON
 
[NestedTypeTest.java][5] - Example of Nested message definition 

[PackageTest.java][6] - Organize protocol buffer files in packages

[ReservedTest.java][7] - Best practices for reserving fields

[VersionTest.java][8] - Experiment with forward and backward compatibility

[WellKnownTypeTest.java][9] - Google supported custom 'Well Known Types' 

### Run
```shell
JAVA_HOME=$(/usr/libexec/java_home -v11) ./gradlew clean test
```

### protoc CLI Example

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

[1]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/AllTypeOuterClassTest.java
[2]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/BinaryStoreTest.java
[3]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/ImporterTest.java
[4]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/JsonConversionTest.java
[5]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/NestedTypeTest.java
[6]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/PackageTest.java
[7]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/ReservedTest.java
[8]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/VersionTest.java
[9]: https://github.com/ronygomes/reference/blob/master/Protobuf/src/test/java/me/ronygomes/proto/WellKnownTypeTest.java