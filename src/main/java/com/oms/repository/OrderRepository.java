package com.oms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.entity.Orders;

/**
 * Repository for performing CRUD operations on Orders POJO
 * @author manoj
 *
 */
@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{

}
