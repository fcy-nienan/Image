package BIgData.Spark;

import org.apache.spark.SparkContext;

import java.io.PrintWriter;

public class WordCount {
    public static void main (String args[]) throws Exception{
        PrintWriter writer=new PrintWriter("E:\\fcy.txt");
        writer.println("---------------");
        writer.flush();
        while (true){
            Thread.sleep(1000);
        }
    }
}
