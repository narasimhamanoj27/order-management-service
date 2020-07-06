package com.oss.service;

import org.springframework.stereotype.Component;

import com.oss.entity.OrderItem;

import java.util.List;

@Component
public interface IOrderItemService {

	List<OrderItem> getAllOrderItems();

	OrderItem getOrderItemDetail(int id);
	
	List<OrderItem> postOrderItemDetail(List<OrderItem> orderItem);
}
