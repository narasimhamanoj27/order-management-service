package com.oss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.api.OrderItem;
import com.oss.service.IOrderItemService;

/**
 * Order Item Service controller for GET-ALL / GET / POST functionalities
 * @author manoj
 *
 */
@RestController
@RequestMapping("/v1")
public class OrderItemServiceController {

	@Autowired
	private IOrderItemService iOrderItemService;

	/**
	 * Controller for retrieving all the OrderItem details from the database
	 * @return ResponseEntity<List<OrderItem>>
	 */
	@GetMapping("/orderItem")
	public ResponseEntity<List<OrderItem>> getAllOrderItems() {

		List<OrderItem> orderItems = iOrderItemService.getAllOrderItems();
		return new ResponseEntity<List<OrderItem>>(orderItems, HttpStatus.OK);
	}

	/**
	 * Controller for retrieving Order Item details for a given CustomerId
	 * @param id customerId
	 * @return ResponseEntity<OrderItem>
	 */
	@GetMapping("/orderItem/{id}")
	public ResponseEntity<OrderItem> getOrderItem(@PathVariable("id") final int id) {

		OrderItem orderItem = iOrderItemService.getOrderItemDetail(id);
		return new ResponseEntity<OrderItem>(orderItem, HttpStatus.OK);
	}
	
	/**
	 * Controller for saving a new record of OrderItem in the H2 DB
	 * @param orderItem
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/orderItem")
	public ResponseEntity<?> postOrderItem(@RequestBody List<OrderItem> orderItem) {
		
		iOrderItemService.postOrderItemDetail(orderItem);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
