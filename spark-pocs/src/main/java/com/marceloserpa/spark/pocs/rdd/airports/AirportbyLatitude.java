package com.marceloserpa.spark.pocs.rdd.airports;


import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.marceloserpa.spark.pocs.Utils;

public class AirportbyLatitude {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[2]");
		try(JavaSparkContext sparkContext = new JavaSparkContext(conf)){
			JavaRDD<String> airports = sparkContext.textFile("files/airports.txt");
			
			JavaRDD<String> airportsBigger = airports.filter(line -> Float.valueOf(line.split(Utils.COMMA_DELIMITER)[6]) > 40);
			
			JavaRDD<String> airportsNameAndCity = airportsBigger.map(line -> {
				String[] splits = line.split(",");
				return StringUtils.join(new String[]{splits[1], splits[6]}, ",");
			});
			
			airportsNameAndCity.saveAsTextFile("results/airports_by_latitude.txt");			
		}
		
	}
	
}
