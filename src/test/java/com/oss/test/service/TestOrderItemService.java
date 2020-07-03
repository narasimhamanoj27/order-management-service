package com.oss.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

import com.oss.api.OrderItem;
import com.oss.impl.OrderItemServiceImpl;
import com.oss.repository.OrderItemRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderItemService {

	@Mock
	private OrderItemRepository orderItemRepository;
	
	@InjectMocks
	private OrderItemServiceImpl orderItemServiceImpl;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPostOrderItems() {
		OrderItem orderItem = new OrderItem(1, "XXX", "Biscuits", 10);
		List<OrderItem> orderItemListExpected = new ArrayList<OrderItem>();
		orderItemListExpected.add(orderItem);
		Mockito.when(orderItemRepository.save(orderItemListExpected.get(0))).thenReturn(orderItem);
		List<OrderItem> orderItemListActual = orderItemServiceImpl.postOrderItemDetail(orderItemListExpected);
		assertNotNull(orderItemListActual);
		assertEquals(orderItemListActual.size(), orderItemListActual.size());
	}
	
	@Test
	public void testGetAllOrderItems() {
		OrderItem orderItem = new OrderItem(1, "XXX", "Biscuits", 10);
		List<OrderItem> orderItemListExpected = new ArrayList<OrderItem>();
		orderItemListExpected.add(orderItem);
		Mockito.when(orderItemRepository.findAll()).thenReturn(orderItemListExpected);
		List<OrderItem> orderItemListActual = orderItemServiceImpl.getAllOrderItems();
		assertNotNull(orderItemListActual);
		assertEquals(orderItemListExpected.size(), orderItemListActual.size());
	}
	
	@Test
	public void testGetOrderItemsByID() {
		Optional<OrderItem> optionalOrderItem = Optional.ofNullable(new OrderItem(1, "XXX", "Biscuits", 10));
		Mockito.when(orderItemRepository.findById(1)).thenReturn(optionalOrderItem);
		OrderItem orderItemActual = orderItemServiceImpl.getOrderItemDetail(1);
		assertNotNull(orderItemActual);
		assertEquals(optionalOrderItem.get(), orderItemActual);
	}
}
