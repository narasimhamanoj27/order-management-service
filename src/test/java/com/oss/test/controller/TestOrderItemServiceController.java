package com.oss.test.controller;

import com.oss.controller.OrderItemServiceController;
import com.oss.entity.OrderItem;
import com.oss.service.IOrderItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderItemServiceController {

	@Mock
	private IOrderItemService iOrderItemService;
	
	@InjectMocks
	private OrderItemServiceController orderItemServiceController;
	
	@Test
	public void testOrderItemServiceController_GetAll() {
		OrderItem orderItem = new OrderItem(1, "GGG", "Scales", 40);
		List<OrderItem> orderItemList = new ArrayList<>();
		orderItemList.add(orderItem);
		Mockito.when(iOrderItemService.getAllOrderItems()).thenReturn(orderItemList);
		ResponseEntity<List<OrderItem>> responseEntity = orderItemServiceController.getAllOrderItems();
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		List<OrderItem> orderItemListActual = responseEntity.getBody();
		assertEquals(orderItemList.size(), orderItemListActual.size());
	}
	
	@Test
	public void testOrderItemServiceController_GetByID() {
		OrderItem orderItem = new OrderItem(1, "GGG", "Scales", 40);
		Mockito.when(iOrderItemService.getOrderItemDetail(1)).thenReturn(orderItem);
		ResponseEntity<OrderItem> responseEntity = orderItemServiceController.getOrderItem(1);
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		OrderItem orderItemActual = responseEntity.getBody();
		assertEquals(orderItem, orderItemActual);
	}
	
	@Test
	public void testOrderItemServiceController_Post() {
		OrderItem orderItem = new OrderItem(1, "GGG", "Scales", 40);
		List<OrderItem> orderItemListExpected = new ArrayList<OrderItem>();
		orderItemListExpected.add(orderItem);
		ResponseEntity<?> responseEntity = orderItemServiceController.postOrderItem(orderItemListExpected);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
