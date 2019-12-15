package com.marceloserpa.spark.pocs.pairrdd;

import java.io.Serializable;

public class AvgCount implements Serializable{

	private static final long serialVersionUID = 1L;

	private int count;
	private double total;
	
	public AvgCount(int count, double total) {
		this.count = count;
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public double getTotal() {
		return total;
	}
	
}
