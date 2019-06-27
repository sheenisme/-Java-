package com.sheen.water.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.sheen.water.data.dao.CustomersDAO;
import com.sheen.water.data.po.Customers;

@Service
@Transactional
public class CustomersServiceImpl implements CustomersService{	
	//自动注入DAO
	@Autowired
	private CustomersDAO dao;
	
	@Test
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public synchronized void findAll() {
		try {
			dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public  synchronized boolean selectOne(Customers po) {
		try {
			List<Customers> list=dao.findByName(po.getName().toString().trim());
			for(Customers vo:list) {
				if(vo.getPassword().toString().equals(po.getPassword().toString()))
						return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public synchronized int insertCustomer(Customers vo) {
		int row = 0;
		try {
			//System.out.print(dao);
			row = this.dao.doCreate(vo);
			if(row > 0) {
				return row;
			}else
				return -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		return row;
	}
	
}
