package com.sheen.water.data.po;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class OrderItems implements Serializable {
	Locale cn=new Locale("zh","cn");
	NumberFormat nf=NumberFormat.getCurrencyInstance(cn);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order_no;
	private double kind;
	private double price;
	private int quantity;
	private double sub_total;
	public String getSub_total() {
		return nf.format(sub_total);
	}
	public String getPrice() {
		return nf.format(price);
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public double getKind() {
		return kind;
	}
	public void setKind(double kind) {
		this.kind = kind;
	}
	public void update() {
		this.setSub_total(price * quantity); 
	}
}
