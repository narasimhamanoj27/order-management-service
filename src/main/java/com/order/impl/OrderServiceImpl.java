package com.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Orders;
import com.order.repository.OrderRepository;
import com.order.service.IOrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	OrderRepository orderRepository;

	/**
	 * Implementation for retrieving all the Order details from H2 DB
	 */
	@Override
	public List<Orders> getAllOrderDetails() {
		List<Orders> orders = new ArrayList<Orders>();
		orderRepository.findAll().forEach(order -> orders.add(order));
		return orders;
	}

	/**
	 * Implementation for retrieving Order details for a given customer ID from H2 DB
	 */
	@Override
	public Orders getOrderDetail(int id) {
		return orderRepository.findById(id).get();
	}
	
	/**
	 * Implementation for saving a list of Order details in H2 DB
	 */
	@Override
	public List<Orders> postAllOrderDetails(List<Orders> orders){
		orders.forEach(order -> orderRepository.save(order));
		return orders;
	}

}
