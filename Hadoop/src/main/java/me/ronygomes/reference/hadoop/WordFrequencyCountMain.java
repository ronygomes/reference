package me.ronygomes.reference.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordFrequencyCountMain extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new WordFrequencyCountMain(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] ioFilePaths) throws Exception {

        Job job = Job.getInstance(getConf());
        job.setJobName("WordFrequencyCounter");
        job.setJarByClass(WordFrequencyCountMain.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(WordFrequencyCountMapper.class);
        job.setReducerClass(WordFrequencyCountReducer.class);

        Path inputFilePath = new Path(ioFilePaths[0]);
        Path outputFilePath = new Path(ioFilePaths[1]);

        FileInputFormat.addInputPath(job, inputFilePath);
        FileOutputFormat.setOutputPath(job, outputFilePath);

        return job.waitForCompletion(true) ? 0 : -1;
    }
}
