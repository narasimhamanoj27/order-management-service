package com.oss.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oss.api.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{

}
