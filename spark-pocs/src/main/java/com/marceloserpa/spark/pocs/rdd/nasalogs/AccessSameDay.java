package com.marceloserpa.spark.pocs.rdd.nasalogs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AccessSameDay {

	public static void main(String[] args) {
		
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("AccessSameDay");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		
		JavaRDD<String> firstJulyHosts = sparkContext.textFile("files/nasa_19950701.tsv").map(log -> log.split("-")[0]);
		JavaRDD<String> firstAugustHosts = sparkContext.textFile("files/nasa_19950801.tsv").map(log -> log.split("-")[0]);			
		
		JavaRDD<String> bothDaysLogs = firstJulyHosts.intersection(firstAugustHosts);
		
		JavaRDD<String> hosts = bothDaysLogs.filter(line -> {
			return !(line.startsWith("host") && line.contains("bytes"));
		});
		
		hosts.saveAsTextFile("results/hosts.txt");
	}
	
}
