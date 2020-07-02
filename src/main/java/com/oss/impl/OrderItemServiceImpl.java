package com.oss.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oss.api.OrderItem;
import com.oss.repository.OrderItemRepository;
import com.oss.service.IOrderItemService;

@Service
public class OrderItemServiceImpl implements IOrderItemService{

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItemRepository.findAll().forEach(orderItem -> orderItems.add(orderItem));
		return orderItems;
	}

	@Override
	public OrderItem getOrderDetail(int id) {
		
		return orderItemRepository.findById(id).get();
	}

	
}
