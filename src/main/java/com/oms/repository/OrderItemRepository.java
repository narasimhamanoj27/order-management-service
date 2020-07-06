package com.oms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oms.entity.OrderItem;

/**
 * Repository for performing CRUD operations on OrderItem POJO
 * @author manoj
 *
 */
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{

}
