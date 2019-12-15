package com.marceloserpa.spark.pocs.pairrdd;

import java.util.Map.Entry;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple12;
import scala.Tuple2;

public class HousePrice {

	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[2]");
		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		
		JavaRDD<String> lines = sparkContext.textFile("files/RealState.csv");
		
		JavaRDD<String> cleanedLines = lines.filter(line -> !line.contains("Bedrooms") && !line.isEmpty());
		
		JavaPairRDD<String, AvgCount> housePricePairRdd = cleanedLines.mapToPair(line -> {
			String[] splits = line.split(",");
			return new Tuple2<>(splits[3], new AvgCount(1,  Double.parseDouble(splits[2])));
		});
		
		JavaPairRDD<String, AvgCount> housePriceTotal = housePricePairRdd.reduceByKey((x, y) -> new AvgCount(x.getCount() + y.getCount(), x.getTotal() + y.getTotal()));
		
		JavaPairRDD<String, Double> housePriceAVG = housePriceTotal.mapValues(avgCount -> avgCount.getTotal() / avgCount.getCount());
		
		for (Entry<String, Double> entry : housePriceAVG.collectAsMap().entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}		
		
	}
	
}
