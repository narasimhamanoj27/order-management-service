package com.order.service;

import org.springframework.stereotype.Component;

import com.order.entity.Orders;

import java.util.List;

@Component
public interface IOrderService {

	List<Orders> getAllOrderDetails();

	Orders getOrderDetail(int id);
	
	List<Orders> postAllOrderDetails(List<Orders> orders);
}
