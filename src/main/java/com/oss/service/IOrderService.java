package com.oss.service;

import java.util.List;

import com.oss.api.Order;

public interface IOrderService {

	List<Order> getAllOrderDetails();

	Order getOrderDetail(int id); 
}
