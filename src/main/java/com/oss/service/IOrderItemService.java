package com.oss.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oss.api.OrderItem;

@Component
public interface IOrderItemService {

	List<OrderItem> getAllOrderItems();

	OrderItem getOrderItemDetail(int id);
	
	List<OrderItem> postOrderItemDetail(List<OrderItem> orderItem);
}
