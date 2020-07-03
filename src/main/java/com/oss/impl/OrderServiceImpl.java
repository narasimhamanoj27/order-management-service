package com.oss.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oss.api.Orders;
import com.oss.repository.OrderRepository;
import com.oss.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Orders> getAllOrderDetails() {
		List<Orders> orders = new ArrayList<Orders>();
		orderRepository.findAll().forEach(order -> orders.add(order));
		return orders;
	}

	@Override
	public Orders getOrderDetail(int id) {
		return orderRepository.findById(id).get();
	}
	
	@Override
	public List<Orders> postAllOrderDetails(List<Orders> orders){
		orders.forEach(order -> orderRepository.save(order));
		return orders;
	}

}
