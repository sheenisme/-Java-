package com.sheen.water.service;

import com.sheen.water.data.po.Customers;

public interface CustomersService {
	public boolean selectOne(Customers po);
	public int insertCustomer(Customers vo);
}
