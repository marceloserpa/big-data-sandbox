package com.marceloserpa.spark.pocs;

import java.util.Arrays;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class WordCount {

	public static void main(String[] args) {
		
		SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[1]");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		
		JavaRDD<String> lines = sparkContext.textFile("files/word-count.txt");
		JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
		
		Map<String, Long> wordsCounts = words.countByValue();
		
		for(Map.Entry<String, Long> entry : wordsCounts.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
	}
	
}
