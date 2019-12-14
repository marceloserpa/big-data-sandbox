package com.marceloserpa.spark.pocs.rdd.airports;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AirportsUsa {

	public static void main(String[] args) {
		
		SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[2]");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		
		JavaRDD<String> airports = sparkContext.textFile("files/airports.txt");
		
		JavaRDD<String> airportsUsa = airports.filter(line -> line.split(",")[3].equals("\"United States\""));
		
		JavaRDD<String> airportsNameAndCity = airportsUsa.map(line -> {
			String[] splits = line.split(",");
			return StringUtils.join(new String[] {splits[1], splits[2]}, ",");
		});
		
		airportsNameAndCity.saveAsTextFile("results/airports_in_usa.txt");
	}
	
	
}
