package com.oms.controller;

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

import com.oms.configuration.CustomException;
import com.oms.constants.ErrorConstants;
import com.oms.entity.OrderItem;
import com.oms.service.IOrderItemService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Order Item Service controller for GET-ALL / GET / POST functionalities
 * 
 * @author manoj
 *
 */
@RestController
@RequestMapping("/v1/order-item-service")
public class OrderItemServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemServiceController.class);

	@Autowired
	private IOrderItemService iOrderItemService;

	/**
	 * Controller for retrieving all the OrderItem details from the database
	 * 
	 * @return ResponseEntity<List<OrderItem>>
	 */
	@ApiResponses(value = { @ApiResponse(message = "Successful response", code = 200, response = OrderItem.class),
			@ApiResponse(message = "Not Found", code = 404),
			@ApiResponse(message = "UnAuthorized", code = 401),
			@ApiResponse(message = "Internal Server Error", code = 500),
			@ApiResponse(message = "UnProcessable Entity", code = 422) })
	@ApiOperation(value = "Operation to retrieve all the Order Items", notes = "Operation to retrieve all the Order Items")
	@GetMapping("/orderItem")
	public ResponseEntity<?> getAllOrderItems() {

		List<OrderItem> orderItems = null;
		try {
			orderItems = iOrderItemService.getAllOrderItems();
			if (orderItems.size() <= 0) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ErrorConstants.LIST_NOT_FOUND);
			}
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
			CustomException customException = new CustomException(ex.getStatusCode(), ex.getMessage(),
					"ORDER_DETAILS_NOT_FOUND");
			return new ResponseEntity<>(customException, ex.getStatusCode());
		} catch (InternalServerError ex) {
			LOGGER.error("Failed to retrieve Order Items details from the DB", ex);
			CustomException customException = new CustomException(ex.getStatusCode(), ex.getMessage(),
					"ORDER_ITEMS_NOT_FOUND");
			return new ResponseEntity<>(customException, ex.getStatusCode());
		}
		return new ResponseEntity<>(orderItems, HttpStatus.OK);
	}

	/**
	 * Controller for retrieving Order Item details for a given CustomerId
	 * 
	 * @param id customerId
	 * @return ResponseEntity<OrderItem>
	 */
	@ApiResponses(value = { @ApiResponse(message = "Successful response", code = 200, response = OrderItem.class),
			@ApiResponse(message = "Not Found", code = 404),
			@ApiResponse(message = "UnAuthorized", code = 401),
			@ApiResponse(message = "Internal Server Error", code = 500),
			@ApiResponse(message = "UnProcessable Entity", code = 422) })
	@ApiOperation(value = "Operation to retrieve an Order Item by customer ID", notes = "Operation to retrieve an Order Item by customer ID")
	@GetMapping("/orderItem/{id}")
	public ResponseEntity<?> getOrderItem(@PathVariable("id") final int id) {

		OrderItem orderItem = null;
		try {
			orderItem = iOrderItemService.getOrderItemDetail(id);
			Optional<OrderItem> optionalOrder = Optional.ofNullable(orderItem);
			if (!optionalOrder.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ErrorConstants.ORDERITEM_NOT_FOUND);
			}
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Failed to retrieve Order Item details from the DB", ex);
			CustomException customException = new CustomException(ex.getStatusCode(), ex.getMessage(),
					"ORDER_ITEM_NOT_FOUND");
			return new ResponseEntity<>(customException, ex.getStatusCode());
		} catch (InternalServerError ex) {
			LOGGER.error("Failed to retrieve Order Item details from the DB", ex);
			CustomException customException = new CustomException(ex.getStatusCode(), ex.getMessage(),
					"ORDER_ITEM_NOT_FOUND");
			return new ResponseEntity<>(customException, ex.getStatusCode());
		} catch (NoSuchElementException ex) {
			LOGGER.error("Failed to retrieve all the details from the DB", ex);
			CustomException customException = new CustomException(HttpStatus.NOT_FOUND, ex.getMessage(),
					"ORDER_ITEM_NOT_FOUND");
			return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(orderItem, HttpStatus.OK);
	}

	/**
	 * Controller for saving a new record of OrderItem in the H2 DB
	 * 
	 * @param orderItem
	 * @return ResponseEntity<?>
	 */
	@ApiResponses(value = { @ApiResponse(message = "Successful response", code = 200),
			@ApiResponse(message = "Not Found", code = 404),
			@ApiResponse(message = "UnAuthorized", code = 401),
			@ApiResponse(message = "Internal Server Error", code = 500),
			@ApiResponse(message = "UnProcessable Entity", code = 422) })
	@ApiOperation(value = "Operation to save an Order Item into H2", notes = "Operation to save an Order Item into H2")
	@PostMapping("/orderItem")
	public ResponseEntity<?> postOrderItem(@RequestBody List<OrderItem> orderItem) {

		try {
			iOrderItemService.postOrderItemDetail(orderItem);
		} catch (Exception ex) {
			// No need to worry about PrimaryKey and Existing records, CRUDRepository will
			// do INSERT/UPDATE
			LOGGER.error("Failed to save the order item details from the DB", ex);
			return new ResponseEntity<>(ex, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
