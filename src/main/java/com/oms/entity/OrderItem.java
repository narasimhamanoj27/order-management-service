package com.oms.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@NotNull
	private int customerId;
	@Column
	@NotNull
	private String productCode;
	@Column
	@NotNull
	private String productName;
	@Column
	@NotNull
	private int Quantity;

	public OrderItem() {
	}

	public OrderItem(int customerId, String productCode, String productName, int quantity) {
		super();
		this.customerId = customerId;
		this.productCode = productCode;
		this.productName = productName;
		Quantity = quantity;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return customerId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return Quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

}
