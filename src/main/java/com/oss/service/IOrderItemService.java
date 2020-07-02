package com.oss.service;

import java.util.List;

import com.oss.api.OrderItem;

public interface IOrderItemService {

	List<OrderItem> getAllOrderItems();

	OrderItem getOrderDetail(int id);
}
