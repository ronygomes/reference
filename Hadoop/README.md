# Hadoop Reference

Counted Word Frequency using Map Reduce Programming model with Hadoop Framework. Data is read from 
`./data/input` directory.

**Note:** If `/data/output` directory exists, it will throw following exception: 

```
Exception in thread "main" org.apache.hadoop.mapred.FileAlreadyExistsException: Output directory file:/home/$USER/Hadoop/data/output/ already exists
```

Assuming Java is installed, running following command will run the map reduce job and will store output in `./data/output` directory.
```shell script
$ ./gradlew run --args="./data/input/ ./data/output/"
```