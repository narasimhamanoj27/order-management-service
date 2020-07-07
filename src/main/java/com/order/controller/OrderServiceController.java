package com.order.controller;

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

import com.order.configuration.CustomException;
import com.order.configuration.CustomFeignClient;
import com.order.constants.ErrorConstants;
import com.order.entity.OrderItem;
import com.order.entity.Orders;
import com.order.service.IOrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

	@Autowired
	private CustomFeignClient customFeignClient;

	/**
	 * Controller for retrieving all the Order details from the database
	 * 
	 * @return ResponseEntity<List<Orders>>
	 */
	@ApiResponses(value = { @ApiResponse(message = "Successful response", code = 200, response = Orders.class),
			@ApiResponse(message = "Not Found", code = 404), @ApiResponse(message = "UnAuthorized", code = 401),
			@ApiResponse(message = "Internal Server Error", code = 500),
			@ApiResponse(message = "UnProcessable Entity", code = 422) })
	@ApiOperation(value = "Operation to retrieve all Orders", notes = "Operation to retrieve all Orders")
	@GetMapping("/order")
	public ResponseEntity<?> getAllOrders() {
		List<Orders> orders = null;
		try {
			orders = iOrderService.getAllOrderDetails();
			orders.forEach(order -> {
				List<OrderItem> orderItems = customFeignClient.getOrderItem(order.getCustomerId());
				if (orderItems.size() > 0) {
					order.setOrderItems(orderItems);
				} else {
					throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ErrorConstants.LIST_NOT_FOUND);
				}
			});
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
	@ApiResponses(value = { @ApiResponse(message = "Successful response", code = 200, response = Orders.class),
			@ApiResponse(message = "Not Found", code = 404), @ApiResponse(message = "UnAuthorized", code = 401),
			@ApiResponse(message = "Internal Server Error", code = 500),
			@ApiResponse(message = "UnProcessable Entity", code = 422) })
	@ApiOperation(value = "Operation to retrieve an Order by customer ID", notes = "Operation to retrieve an Order by customer ID")
	@GetMapping("/order/{id}")
	public ResponseEntity<?> getOrder(@PathVariable("id") final int id) {
		Orders order = null;

		try {
			order = iOrderService.getOrderDetail(id);
			List<OrderItem> orderItems = customFeignClient.getOrderItem(order.getCustomerId());
			if(orderItems.size() <= 0) {
				Optional<Orders> optionalOrder = Optional.ofNullable(order);
				if (!optionalOrder.isPresent()) {
					throw new HttpClientErrorException(HttpStatus.NOT_FOUND, ErrorConstants.ORDER_NOT_FOUND);
				}
			} else {
				order.setOrderItems(orderItems);
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
	@ApiResponses(value = { @ApiResponse(message = "Successful response", code = 200),
			@ApiResponse(message = "Not Found", code = 404), @ApiResponse(message = "UnAuthorized", code = 401),
			@ApiResponse(message = "Internal Server Error", code = 500),
			@ApiResponse(message = "UnProcessable Entity", code = 422) })
	@ApiOperation(value = "Operation to save an Order into H2", notes = "Operation to save an Order into H2")
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
