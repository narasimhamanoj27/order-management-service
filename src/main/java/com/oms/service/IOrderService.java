package com.oms.service;

import org.springframework.stereotype.Component;

import com.oms.entity.Orders;

import java.util.List;

@Component
public interface IOrderService {

	List<Orders> getAllOrderDetails();

	Orders getOrderDetail(int id);
	
	List<Orders> postAllOrderDetails(List<Orders> orders);
}
