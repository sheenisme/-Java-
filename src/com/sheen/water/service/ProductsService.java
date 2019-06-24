package com.sheen.water.service;

import java.util.List;
import com.sheen.water.data.po.Products;

public interface ProductsService{
	public List<Products> findAll();
	public boolean create(Products po);
}
