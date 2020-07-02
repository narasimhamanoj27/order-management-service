package com.oss.repository;

import org.springframework.data.repository.CrudRepository;

import com.oss.api.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

}
