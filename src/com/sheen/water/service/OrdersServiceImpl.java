package com.sheen.water.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.sheen.water.data.dao.OrdersDAO;
import com.sheen.water.data.po.Orders;

@Service
@Transactional
public class OrdersServiceImpl  implements OrdersService{
	//自动注入DAO
	@Autowired
	private OrdersDAO dao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public synchronized List<Orders> findAll() {
		List<Orders> list = null;
		try {
			list=dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
	}
		return list;
	}

}
