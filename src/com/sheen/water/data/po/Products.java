package com.sheen.water.data.po;

import java.io.Serializable;

public class Products implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double kind;
	private double price;

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getKind() {
		return kind;
	}
	public void setKind(double kind) {
		this.kind = kind;
	}
}
