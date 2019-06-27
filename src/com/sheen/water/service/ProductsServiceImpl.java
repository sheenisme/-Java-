package com.sheen.water.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.sheen.water.data.dao.ProductsDAO;
import com.sheen.water.data.po.Products;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService{	
	//自动注入DAO
	@Autowired
	private ProductsDAO dao;
	 
	@Test
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public synchronized List<Products> findAll() {
		List<Products> list=null;
		try {
			list = dao.findAll();
			for(Products po:list) {
				System.out.println("Kind:"+po.getKind()+"  Price:"+po.getPrice()+"\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT, readOnly = false)
	public synchronized boolean create(Products po) {
		boolean flag=false;
		try {
			this.dao.doCreate(po);
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return flag;
	}
}
