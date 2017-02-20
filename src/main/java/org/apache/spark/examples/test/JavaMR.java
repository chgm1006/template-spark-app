package org.apache.spark.examples.test;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

/**
 * Created by Forrest on 2017. 2. 20..
 */
public class JavaMR {


    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Usage: sql example 1 <file>");
            System.exit(1);
        }

        SparkSession spark = SparkSession
                .builder()
                .master("local[1]")
                .appName("sql example 1")
                .getOrCreate();

        SQLContext sqlContext = new SQLContext(spark);

        // data :: 22 개
        JavaRDD<Columns> javaRDD =
                spark.read()
                        .textFile(args[0])
                        .javaRDD()
                        .map(new Function<String, Columns>() {
                            @Override
                            public Columns call(String line) throws Exception {
                                System.out.println("line :: " + line);
                                String[] parts = line.split(",");
                                for (String s : parts) {
                                    System.out.println("parts :: " + s);
                                }
                                Columns columns = new Columns(parts);
                                return columns;
                            }
                        });
        Dataset<Row> columnDF = spark.createDataFrame(javaRDD, Columns.class);
        columnDF.createOrReplaceTempView("Columns");
        columnDF.show();
    }
}
