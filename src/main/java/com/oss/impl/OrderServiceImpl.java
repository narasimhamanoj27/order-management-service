package com.oss.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.oss.api.Order;
import com.oss.repository.OrderRepository;
import com.oss.service.IOrderService;

public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrderDetails() {
		List<Order> orders = new ArrayList<Order>();
		orderRepository.findAll().forEach(order -> orders.add(order));
		return orders;
	}

	@Override
	public Order getOrderDetail(int id) {
		return orderRepository.findById(id).get();
	}

}
