package com.marceloserpa.spark.pocs;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SortedWordCount {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("sortedWordCounts").setMaster("local[1]");
		try(JavaSparkContext sparkContext = new JavaSparkContext(conf)){
			JavaRDD<String> lines = sparkContext.textFile("files/word-count.txt");
			JavaRDD<String> wordsRDD = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

			JavaPairRDD<String, Integer> wordPairRDD = wordsRDD.mapToPair(word -> new Tuple2<>(word, 1));
			JavaPairRDD<String, Integer> wordsCounts = wordPairRDD.reduceByKey((x, y) -> x + y);

			JavaPairRDD<Integer, String> countToWordPairs = wordsCounts.mapToPair(wordToCount -> new Tuple2<>(wordToCount._2(), wordToCount._1()));
			JavaPairRDD<Integer, String> sortedWordToCountPairs = countToWordPairs.sortByKey(false);
			
			JavaPairRDD<String, Integer> sortedCountToWordPairs = sortedWordToCountPairs.mapToPair(wordToCount -> new Tuple2<>(wordToCount._2(), wordToCount._1()));
			
			for (Tuple2<String, Integer> entry : sortedCountToWordPairs.collect()) {
				System.out.println(entry._1() + " : " + entry._2());
			}			
		}
	}
	
}
