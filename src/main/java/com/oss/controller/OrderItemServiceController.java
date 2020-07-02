package com.oss.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.api.Order;
import com.oss.service.IOrderItemService;

@RestController
@RequestMapping("/v1/OMS")
public class OrderItemServiceController {

	private IOrderItemService iOrderItemService;

	@GetMapping("/orderItem")
	public ResponseEntity<List<Order>> getAllOrderItems() {

		List<Order> orders = iOrderItemService.getAllOrderItems();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping("/orderItem/{id}")
	public ResponseEntity<Order> getOrderItem() {

		Order order = iOrderItemService.getOrderDetail();
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

}
