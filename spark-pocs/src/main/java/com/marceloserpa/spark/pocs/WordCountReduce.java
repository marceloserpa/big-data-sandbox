package com.marceloserpa.spark.pocs;

import java.util.Arrays;
import java.util.Map.Entry;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class WordCountReduce {

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[1]");
		try(JavaSparkContext sparkContext = new JavaSparkContext(conf)){
			JavaRDD<String> lines = sparkContext.textFile("files/word-count.txt");
			JavaRDD<String> wordsRDD = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

			JavaPairRDD<String, Integer> wordPairRDD = wordsRDD.mapToPair(word -> new Tuple2<>(word, 1));

			JavaPairRDD<String, Integer> wordsCounts = wordPairRDD.reduceByKey((x, y) -> x + y);

			for (Entry<String, Integer> entry : wordsCounts.collectAsMap().entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}			
		}

	}

}
