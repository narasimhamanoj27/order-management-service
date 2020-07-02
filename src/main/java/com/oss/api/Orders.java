package com.oss.api;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
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

}
