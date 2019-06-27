package com.sheen.water.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.sheen.water.data.dao.OrderItemsDAO;
import com.sheen.water.data.po.OrderItems;

@Service
@Transactional
public class OrderItemsServiceImp implements OrderItemsService{
	//自动注入DAO
	@Autowired
	private OrderItemsDAO dao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public synchronized List<OrderItems> findAll() {
		List<OrderItems> list = null;
		try {
			list=dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
	}
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public synchronized boolean create(OrderItems po) {
		boolean flag=false;
		try {
			this.dao.doCreate(po);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return flag;
	}

}
