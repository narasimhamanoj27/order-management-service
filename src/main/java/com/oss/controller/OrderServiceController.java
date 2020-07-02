package com.oss.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.api.Order;
import com.oss.service.IOrderService;

@RestController
@RequestMapping("/v1/OMS")
public class OrderServiceController {
	
	private IOrderService iOrderService;

	@GetMapping("/order")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = iOrderService.getAllOrderDetails();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") final int id){
		Order order = iOrderService.getOrderDetail();
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
}
