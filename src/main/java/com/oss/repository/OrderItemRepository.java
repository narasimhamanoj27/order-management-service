package com.oss.repository;

import org.springframework.data.repository.CrudRepository;

import com.oss.api.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{

}
