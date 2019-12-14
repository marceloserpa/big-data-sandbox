package com.marceloserpa.spark.pocs.rdd.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;

public class PersistenceSample {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("Persistence");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		
		List<String> languages = Arrays.asList("Java", "Scala", "Go", "Rust", "JS", "Rust");
		JavaRDD<String> rdd = sparkContext.parallelize(languages);
		
		rdd.persist(StorageLevel.MEMORY_ONLY());
		
		long words = rdd.count();
		System.out.println(words);
		
		Map<String, Long> countByValue = rdd.countByValue();
		System.out.println(countByValue.size());
	}
	
}
