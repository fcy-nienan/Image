package com.fcy.BigData.Scala;

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.io.Path
object spark {
  def main(args:Array[String]):Unit={
    val schema=StructType(
      StructField("field",StringType,true)::
      StructField("vehicle",StringType,true)::
      StructField("vin",StringType,true)::
      StructField("mark",StringType,true)::Nil
    );
    println(schema.fieldIndex("field"))
    println(schema.fieldIndex("vehicle"))
    println(schema.fieldIndex("vin"))
    println(schema.fieldIndex("mark"))
    schema.printTreeString();
    schema.fieldNames.map(x=>print(x));

  }
}
