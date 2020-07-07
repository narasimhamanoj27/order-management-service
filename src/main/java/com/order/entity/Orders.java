package com.order.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customerId")
	@NotNull
	private int customerId;
	@Column
	@NotNull
	private String customerName;
	@Column
	@NotNull
	private Date orderDate;
	@Column
	@NotNull
	private String address;
	@Column
	@NotNull
	private Double total;
	
	@NotNull
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = true)
	private List<OrderItem> orderItems;
	
	public Orders() {
	}

	public Orders(@NotNull int customerId, @NotNull String customerName, @NotNull Date orderDate,
			@NotNull String address, @NotNull Double total, @NotNull List<OrderItem> orderItems) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.address = address;
		this.total = total;
		this.orderItems = orderItems;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}
