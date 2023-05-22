
//Importing libraries
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class differenceReducer extends MapReduceBase implements Reducer<Text,Text, String, String>{
	
	public void reduce(Text key, Iterator<Text> values, OutputCollector<String, String> output, Reporter reporter) throws IOException {
		// input from mapper
        // ( 1, [x,A] )
		// (2, [x,B])
		ArrayList<String> result = new ArrayList<String>();
		 while (values.hasNext()) {
			 String value=values.next().toString();
			 result.add(value);
		 }
		 if (result.size()==1){
			 String finalResult="("+key+","+result.get(0)+")";
			 output.collect(" ", finalResult);
		 }
	}
}
