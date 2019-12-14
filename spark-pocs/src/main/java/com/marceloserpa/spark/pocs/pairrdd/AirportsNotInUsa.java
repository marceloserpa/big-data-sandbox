package com.marceloserpa.spark.pocs.pairrdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class AirportsNotInUsa {

	public static void main(String[] args) {
		
		SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[2]");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		
		JavaRDD<String> airports = sparkContext.textFile("files/airports.txt");
				
		JavaPairRDD<String, String> airportRDD = airports.mapToPair(line -> {
			String[] splits = line.split(",");
			return new Tuple2<>(splits[1], splits[3]);
		});
		
		JavaPairRDD<String, String> airportsNotInUsa = airportRDD.filter(kv -> !kv.equals("\"United States\""));
		
		airportsNotInUsa.saveAsTextFile("results/airports_not_in_usa");
	}
	
	
}
