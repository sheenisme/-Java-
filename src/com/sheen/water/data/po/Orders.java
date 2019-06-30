package com.sheen.water.data.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Locale;

public class Orders implements Serializable{
	Locale cn=new Locale("zh","cn");
	NumberFormat nf=NumberFormat.getCurrencyInstance(cn);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int no;
	private int customer_no;
	private Timestamp order_time;
	private double total_money;
	private String state;
	private String sender_name;
	private String sender_phone;
	public int getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(int customer_no) {
		this.customer_no = customer_no;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public String getTotal_money() {
		return nf.format(total_money);
	}
	public void setTotal_money(double total_money) {
		this.total_money = total_money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getSender_phone() {
		return sender_phone;
	}
	public void setSender_phone(String sender_phone) {
		this.sender_phone = sender_phone;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
}
