package com.oss.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.api.OrderItem;
import com.oss.service.IOrderItemService;

@RestController
@RequestMapping("/v1/OMS")
public class OrderItemServiceController {

	private IOrderItemService iOrderItemService;

	@GetMapping("/orderItem")
	public ResponseEntity<List<OrderItem>> getAllOrderItems() {

		List<OrderItem> orderItems = iOrderItemService.getAllOrderItems();
		return new ResponseEntity<List<OrderItem>>(orderItems, HttpStatus.OK);
	}

	@GetMapping("/orderItem/{id}")
	public ResponseEntity<OrderItem> getOrderItem(@PathVariable("id") final int id) {

		OrderItem orderItem = iOrderItemService.getOrderDetail(id);
		return new ResponseEntity<OrderItem>(orderItem, HttpStatus.OK);
	}

}
