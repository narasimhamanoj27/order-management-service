package com.oss.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.oss.constants.ErrorConstants;
import com.oss.entity.CustomException;
import com.oss.entity.Orders;
import com.oss.service.IOrderService;

/**
 * Order Service controller for GET-ALL / GET / POST functionalities
 * 
 * @author manoj
 *
 */
@RestController
@RequestMapping("/v1/order-service")
public class OrderServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceController.class);

	@Autowired
	private IOrderService iOrderService;

	/**
	 * Controller for retrieving all the Order details from the database
	 * 
	 * @return ResponseEntity<List<Orders>>
	 */
	@GetMapping("/order")
	public ResponseEntity<?> getAllOrders() {
		List<Orders> orders = null;
		try {
			orders = iOrderService.getAllOrderDetails();
			if (orders.size() <= 0) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ErrorConstants.LIST_NOT_FOUND);
			}
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
			CustomException customException = new CustomException(ex.getStatusCode(), ex.getMessage(),
					"ORDER_DETAILS_NOT_FOUND");
			return new ResponseEntity<>(customException, ex.getStatusCode());
		}

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	/**
	 * Controller for retrieving Order details based on customer Id
	 * 
	 * @param id customerId
	 * @return ResponseEntity<Orders>
	 */
	@GetMapping("/order/{id}")
	public ResponseEntity<?> getOrder(@PathVariable("id") final int id) {
		Orders order = null;

		try {
			order = iOrderService.getOrderDetail(id);
			Optional<Orders> optionalOrder = Optional.ofNullable(order);
			if (!optionalOrder.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ErrorConstants.ORDER_NOT_FOUND);
			}
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
			CustomException customException = new CustomException(ex.getStatusCode(), ex.getMessage(),
					"ORDER_DETAILS_NOT_FOUND");
			return new ResponseEntity<>(customException, ex.getStatusCode());
		} catch (InternalServerError ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
			CustomException customException = new CustomException(ex.getStatusCode(), ex.getMessage(),
					"ORDER_DETAILS_NOT_FOUND");
			return new ResponseEntity<>(customException, ex.getStatusCode());
		} catch (NoSuchElementException ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
			CustomException customException = new CustomException(HttpStatus.NOT_FOUND, ex.getMessage(),
					"ORDER_DETAILS_NOT_FOUND");
			return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}

	/**
	 * Controller for saving a new record of Order details into H2 DB
	 * 
	 * @param orders
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/order")
	public ResponseEntity<?> postAllOrders(@RequestBody List<Orders> orders) {

		try {
			iOrderService.postAllOrderDetails(orders);
		} catch (Exception ex) {
			// No need to worry about PrimaryKey and Existing records, CRUDRepository will
			// do INSERT/UPDATE
			LOGGER.error("Failed to save the order details from the DB", ex);
			return new ResponseEntity<>(ex, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
