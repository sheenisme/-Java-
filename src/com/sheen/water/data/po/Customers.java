package com.sheen.water.data.po;

import java.io.Serializable;

public class Customers  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int no;
	private String name;
	private String password;
	private String address;
	private String phone;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
