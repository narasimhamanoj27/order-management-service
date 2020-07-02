package com.oss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.api.Orders;
import com.oss.service.IOrderService;

@RestController
@RequestMapping("/v1/OMS")
public class OrderServiceController {
	
	@Autowired
	private IOrderService iOrderService;

	@GetMapping("/order")
	public ResponseEntity<List<Orders>> getAllOrders(){
		List<Orders> orders = iOrderService.getAllOrderDetails();
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Orders> getOrder(@PathVariable("id") final int id){
		Orders order = iOrderService.getOrderDetail(id);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
}
