package com.marceloserpa.spark.pocs.rdd.nasalogs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class UnionLogs {

	public static void main(String[] args) {
		
		SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("nasaLogs");
		JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
		
		JavaRDD<String> julyLogs = sparkContext.textFile("files/nasa_19950701.tsv");
		JavaRDD<String> augustLogs = sparkContext.textFile("files/nasa_19950801.tsv");
		
		JavaRDD<String> aggregatedLogLines = julyLogs.union(augustLogs);
		
		JavaRDD<String> cleanLogs = aggregatedLogLines.filter(line -> {
			return !(line.startsWith("host") && line.contains("bytes"));
		});

		JavaRDD<String> sample = cleanLogs.sample(true, 0.1);
		
		sample.saveAsTextFile("results/sample_nasa_logs.txt");
	}
	
}
