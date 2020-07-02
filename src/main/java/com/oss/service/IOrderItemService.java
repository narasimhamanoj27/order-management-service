package com.oss.service;

import java.util.List;

import com.oss.api.Order;

public interface IOrderItemService {

	List<Order> getAllOrderItems();

	Order getOrderDetail();
}
