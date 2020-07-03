package com.oss.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oss.api.OrderItem;

/**
 * Repository for performing CRUD operations on OrderItem POJO
 * @author manoj
 *
 */
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{

}
