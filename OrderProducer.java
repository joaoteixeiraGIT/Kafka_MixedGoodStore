package um.si;


import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class OrderProducer {

    private final static String TOPIC = "kafka-orders";

    private static KafkaProducer createProducer() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "AvroProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class.getName());

        // Configure the KafkaAvroSerializer.
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());

        // Schema Registry location.
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        return new KafkaProducer(props);
    }

    private static ProducerRecord<Object,Object> generateRecord(Schema schema) {
        Random rand = new Random();
        GenericRecord avroRecord = new GenericData.Record(schema);

        //Generate random data
        int Order_ID = (int)(Math.random()*(10000 - 0 + 1) + 1);
        avroRecord.put("OrderID",Order_ID);
        //avroRecord.put("date",System.currentTimeMillis());
        avroRecord.put("SupplierID",Integer.valueOf(rand.nextInt((10-1)) + 1));
        avroRecord.put("ItemID",Integer.valueOf(rand.nextInt((49-1)) + 1));
        avroRecord.put("ItemCost", new Float (rand.nextInt((30-1) + 1) + "." + rand.nextInt((99 - 1) + 1) ));
        avroRecord.put("ItemQuantity",Integer.valueOf(rand.nextInt((100-1)) + 1));

        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>(TOPIC, Order_ID, avroRecord);
        return producerRecord;
    }


    public static void main(String[] args) throws Exception {
        Schema schema = SchemaBuilder.record("Order")
                .fields()
                .requiredInt("OrderID")
                //.requiredLong("date")
                .requiredInt("SupplierID")
                .requiredInt("ItemID")
                .requiredFloat("ItemCost")
                .requiredInt("ItemQuantity")
                .endRecord();

       KafkaProducer producer = createProducer();


        while(true){
            ProducerRecord record = generateRecord(schema);
            producer.send(record);

            System.out.println("[RECORD] Sent new order object.");
            Thread.sleep(10000);
        }
    }

}
