package com.sheen.water.data.dao;

import java.util.List;

import com.sheen.water.data.po.Customers;

public interface CustomersDAO  extends DAO<Customers, Integer>{
	/**
	 * 通过用户名查找用户
	 * @param name
	 * @return
	 */
	public List<Customers> findByName(String loginName);
}
