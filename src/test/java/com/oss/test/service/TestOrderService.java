package com.oss.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.oss.api.Orders;
import com.oss.impl.OrderServiceImpl;
import com.oss.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderService {

	@Mock
	private OrderRepository orderRepositoryMock;

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Mock
	private Iterator<Orders> iterator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPostOrderService() throws Exception {
		Date date = new Date();
		Orders orders = new Orders(1, "ISHU", new java.sql.Date(date.getTime()), "Hitech City", 30.20);
		List<Orders> orderList = new ArrayList<Orders>();
		orderList.add(orders);
		Mockito.when(orderRepositoryMock.save(orderList.get(0))).thenReturn(orders);
		List<Orders> orderListActual = orderServiceImpl.postAllOrderDetails(orderList);
		assertNotNull(orderListActual);
		assertEquals(orderList.size(), orderListActual.size());
	}

	@Test
	public void testGetOrdersServiceByID() throws Exception {
		Date date = new Date();
		Optional<Orders> ordersOptional = Optional.ofNullable(new Orders(1, "ISHU", new java.sql.Date(date.getTime()), "Hitech City", 30.20));
		Mockito.when(orderRepositoryMock.findById(1)).thenReturn(ordersOptional);
		Orders ordersActual = orderServiceImpl.getOrderDetail(1);
		assertNotNull(ordersActual);
		assertEquals(ordersOptional.get(), ordersActual);
	}
	
	@Test
	public void testGetAllOrdersService() throws Exception {
		Date date = new Date();
		Orders orders = new Orders(1, "ISHU", new java.sql.Date(date.getTime()), "Hitech City", 30.20);
		List<Orders> orderList = new ArrayList<Orders>();
		orderList.add(orders);
		Mockito.when(orderRepositoryMock.findAll()).thenReturn(orderList);
		List<Orders> ordersListActual = orderServiceImpl.getAllOrderDetails();
		assertNotNull(ordersListActual);
		assertEquals(orderList.size(), ordersListActual.size());
	}

}