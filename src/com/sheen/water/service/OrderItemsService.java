package com.sheen.water.service;

import java.util.List;
import com.sheen.water.data.po.OrderItems;

public interface OrderItemsService {
	public List<OrderItems> findAll();
	public boolean create(OrderItems po);
}
