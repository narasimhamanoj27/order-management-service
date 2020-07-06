package com.oms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.entity.OrderItem;
import com.oms.repository.OrderItemRepository;
import com.oms.service.IOrderItemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements IOrderItemService{

	@Autowired
	OrderItemRepository orderItemRepository;
	
	/**
	 * Implementation for retrieving all the Order Item details from H2 DB
	 */
	@Override
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItemRepository.findAll().forEach(orderItem -> orderItems.add(orderItem));
		return orderItems;
	}

	/**
	 * Implementation for retrieving Order Item details based on customer ID from H2 DB
	 */
	@Override
	public OrderItem getOrderItemDetail(int id) {
		return orderItemRepository.findById(id).get();
	}

	/**
	 * Implementation of saving a list of Order item details into H2 DB
	 */
	@Override
	public List<OrderItem> postOrderItemDetail(List<OrderItem> orderItems) {
		orderItems.forEach(orderItem -> orderItemRepository.save(orderItem));
		return orderItems;
	}

}
