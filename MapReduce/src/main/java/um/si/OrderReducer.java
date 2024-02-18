package um.si;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OrderReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    private final DoubleWritable result = new DoubleWritable();

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context)
            throws IOException, InterruptedException {
        double totalCost = 0;

        // Iterate through the values and calculate the total cost
        for (DoubleWritable val : values) {
            totalCost += val.get();
        }


        //Result with the total
        result.set(totalCost);
        context.write(key, result);
    }
}