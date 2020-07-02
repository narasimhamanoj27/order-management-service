package com.oss.repository;

import org.springframework.data.repository.CrudRepository;

import com.oss.api.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer>{

}
