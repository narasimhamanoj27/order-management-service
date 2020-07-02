package com.oss.api;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int customerId;
	@Column
	private String productCode;
	@Column
	private String productName;
	@Column
	private int Quantity;

	
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
