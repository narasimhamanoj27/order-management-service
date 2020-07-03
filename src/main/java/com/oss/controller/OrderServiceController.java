package com.oss.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.api.Orders;
import com.oss.service.IOrderService;

/**
 * Order Service controller for GET-ALL / GET / POST functionalities
 * @author manoj
 *
 */
@RestController
@RequestMapping("/v1")
public class OrderServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceController.class);

	@Autowired
	private IOrderService iOrderService;

	/**
	 * Controller for retrieving all the Order details from the database
	 * @return ResponseEntity<List<Orders>>
	 */
	@GetMapping("/order")
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> orders = null;
		try {
			orders = iOrderService.getAllOrderDetails();
		} catch (Exception ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
		}

		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}

	/**
	 * Controller for retrieving Order details based on customer Id
	 * @param id customerId
	 * @return ResponseEntity<Orders>
	 */
	@GetMapping("/order/{id}")
	public ResponseEntity<Orders> getOrder(@PathVariable("id") final int id) {
		Orders order = null;

		try {
			order = iOrderService.getOrderDetail(id);
		} catch (Exception ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
		}

		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}

	/**
	 * Controller for saving a new record of Order details into H2 DB
	 * @param orders
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/order")
	public ResponseEntity<?> postAllOrders(@RequestBody List<Orders> orders) {

		try {
			iOrderService.postAllOrderDetails(orders);
		} catch (Exception ex) {
			LOGGER.error("Failed to save the order details from the DB", ex);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
