

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class TweetCount extends Configured implements Tool
{

	public static void main(String[] args)throws Exception
	{
		int toolRunnerParam = ToolRunner.run(new TweetCount(), args);
		System.exit(toolRunnerParam);
	}
	
	public int run(String[] arg0) throws Exception {
		Job job = Job.getInstance(getConf(),"");
		job.setJarByClass(getClass());
		FileInputFormat.setInputPaths(job, new Path(arg0[0]));
		
		job.setMapperClass(UniqueMapper.class);
		
		job.setReducerClass(UniqueReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		return job.waitForCompletion(true) ? 0 : 1;
		
	}
	
}
