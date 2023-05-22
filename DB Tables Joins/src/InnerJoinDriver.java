import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class InnerJoinDriver extends Configured implements Tool{

	public int run(String args[]) throws IOException
	{
		if (args.length < 2)
		{
			System.out.println("Please give valid inputs");
			return -1;
		}
		// adjust the map reduce configuration
		JobConf conf = new JobConf(InnerJoinDriver.class);
		// setup the input
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		// setup the output
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		// setup the mapper and reducer
		conf.setMapperClass(InnerJoinMap.class);
		conf.setReducerClass(differenceReducer.class);
		// setup the map output pair (key, value)
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(Text.class);
		// setup the reducer output pair (key, value)
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		// run the MapReduce
		JobClient.runJob(conf);
		return 0;
	}
	
	// Main Method
	public static void main(String args[]) throws Exception
	{
		int exitCode = ToolRunner.run(new InnerJoinDriver(), args);
		System.out.println(exitCode);
	}

}
