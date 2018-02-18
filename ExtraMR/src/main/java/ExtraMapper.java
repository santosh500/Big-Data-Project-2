



import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.simple.parser.ParseException;

public class ExtraMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
		
	public void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException
	{
		String tweet = record.toString();

		context.write(new Text(tweet), new IntWritable(1));

	}
	
	
	
	
	
}

