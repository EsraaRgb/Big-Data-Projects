// Importing libraries
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class InnerJoinMap  extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>{
	public void map(LongWritable key, Text value, OutputCollector<Text,Text> output, Reporter rep) throws IOException{
		String line = value.toString();
		String[] recordValues = line.replaceAll("[()]", "").split(",");

		String tableName = recordValues[0];
		String attribute1 = recordValues[1];
		String attribute2 = recordValues[2];
		
		System.out.println();
		
		if( tableName.equals("T1") ){
			output.collect( new Text(attribute1), new Text(attribute2) );
		}else{
			output.collect( new Text(attribute2), new Text(attribute1) );
		}
	}
}
