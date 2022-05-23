package me.ronygomes.reference.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordFrequencyCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final String WORD_DELIMITER = " ";

    /**
     * This mapper will read data from data/input/words.txt
     * Hadoop framework will provider data in following format {LineNumber: LineContent}
     * That is why Input Key is LongWriteable and Input Value is Text. it is java's Long & String
     * type wrapper
     *
     * @param key     Line Number when read from file
     * @param value   Line Content
     * @param context Hadoop Framework Context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Mapper<LongWritable, Text, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {

        String[] row = value.toString().split(WORD_DELIMITER);

        for (String word : row) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
