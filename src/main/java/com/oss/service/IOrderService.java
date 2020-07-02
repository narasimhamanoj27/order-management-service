package com.oss.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oss.api.Orders;

@Component
public interface IOrderService {

	List<Orders> getAllOrderDetails();

	Orders getOrderDetail(int id);
	
	void postAllOrderDetails(List<Orders> orders);
}
