package um.si;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private final Text supplierKey = new Text();
    private final DoubleWritable orderCost = new DoubleWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] columns = value.toString().split(";");
        String supplierType = columns[3];
        double itemCost = Double.parseDouble(columns[6]);
        int itemQuantity = Integer.parseInt(columns[7]);

        // Calculate order cost for each record
        double cost = itemCost * itemQuantity;


        // Emit key-value pairs for each supplier type with order cost
        supplierKey.set(supplierType);
        orderCost.set(cost);
        context.write(supplierKey, orderCost);

    }
}

