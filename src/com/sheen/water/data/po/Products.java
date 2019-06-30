package com.sheen.water.data.po;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Products implements Serializable{
	Locale cn=new Locale("zh","cn");
	NumberFormat nf=NumberFormat.getCurrencyInstance(cn);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double kind;
	private double price;

	public String getPrice() {
		return nf.format(price);
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
