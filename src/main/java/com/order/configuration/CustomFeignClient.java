package com.order.configuration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.order.entity.OrderItem;

@FeignClient(value = "order-item-management-service", url = "localhost:8081/v1/order-item-service")
public interface CustomFeignClient {
	
	@GetMapping("/orderItem")
	List<OrderItem> getAllOrderItems();
	
	@GetMapping("/orderItem/{id}")
	List<OrderItem> getOrderItem(@PathVariable(value = "id") final int id);
	
	@PostMapping("/orderitem")
	List<OrderItem> postOrderItem(List<OrderItem> orderItems);

}
