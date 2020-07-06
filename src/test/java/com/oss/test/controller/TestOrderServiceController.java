package com.oss.test.controller;

import com.oss.controller.OrderServiceController;
import com.oss.entity.Orders;
import com.oss.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderServiceController {

	
	@Mock
	private IOrderService iOrderService;
	
	@InjectMocks
	private OrderServiceController orderServiceController;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testOrderServiceController_GETALL() {
		Date date = new Date();
		List<Orders> ordersListExpected = new ArrayList<Orders>();
		Orders order = new Orders(1, "MALENA", new java.sql.Date(date.getTime()), "BUDAPEST", 10.30);
		ordersListExpected.add(order);
		Mockito.when(iOrderService.getAllOrderDetails()).thenReturn(ordersListExpected);
		ResponseEntity<?> responseEntity = orderServiceController.getAllOrders();
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		List<Orders> orderListActual = (List<Orders>) responseEntity.getBody();
		assertNotNull(orderListActual);
		assertEquals(ordersListExpected.size(), orderListActual.size());
	}
	
	@Test
	public void testOrderServiceController_GetByID() {
		Date date = new Date();
		Orders order = new Orders(1, "MALENA", new java.sql.Date(date.getTime()), "BUDAPEST", 10.30);
		Mockito.when(iOrderService.getOrderDetail(1)).thenReturn(order);
		ResponseEntity<Orders> responseEntity = orderServiceController.getOrder(1);
		assertNotNull(responseEntity);
		Orders orderActual = responseEntity.getBody();
		assertEquals(order, orderActual);
	}
	
	@Test
	public void testOrderServiceController_Post() {
		Date date = new Date();
		List<Orders> ordersListExpected = new ArrayList<Orders>();
		Orders order = new Orders(1, "MALENA", new java.sql.Date(date.getTime()), "BUDAPEST", 10.30);
		ordersListExpected.add(order);
		Mockito.when(iOrderService.postAllOrderDetails(ordersListExpected)).thenReturn(ordersListExpected);
		ResponseEntity<?> responseEntity = orderServiceController.postAllOrders(ordersListExpected);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
