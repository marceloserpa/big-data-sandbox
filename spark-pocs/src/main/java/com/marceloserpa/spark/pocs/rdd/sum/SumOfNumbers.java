package com.marceloserpa.spark.pocs.rdd.sum;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SumOfNumbers {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("PrimeNumbers");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		
        JavaRDD<Integer> numbers = sparkContext.textFile("files/prime_nums.txt")
        		.flatMap(line -> Arrays.asList(line.split("\\s+")).iterator())
        		.filter(number -> !number.isEmpty())
        		.map(number -> Integer.valueOf(number));

        System.out.println("Sum: " + numbers.reduce((x, y) -> x + y));		
	}
	
}
