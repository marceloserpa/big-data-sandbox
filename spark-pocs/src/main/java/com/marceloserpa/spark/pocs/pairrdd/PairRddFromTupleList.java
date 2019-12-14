package com.marceloserpa.spark.pocs.pairrdd;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class PairRddFromTupleList {

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("pairRDD").setMaster("local");
		try (JavaSparkContext sparkContext = new JavaSparkContext(conf)) {

			List<Tuple2<String, Integer>> tuples = Arrays.asList(new Tuple2<>("Person1", 23),
					new Tuple2<>("Person2", 19), new Tuple2<>("Person3", 21), new Tuple2<>("Person4", 45),
					new Tuple2<>("Person5", 39));

			JavaRDD<Tuple2<String, Integer>> pairRdd = sparkContext.parallelize(tuples);
		
			
			pairRdd.saveAsTextFile("results/tuples");
		}

	}

}
