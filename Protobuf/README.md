
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
