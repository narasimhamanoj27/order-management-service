package com.order.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NotNull
@Entity
@Table(name = "orderitem")
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@NotNull
	private String productCode;
	@Column
	@NotNull
	private String productName;
	@Column
	@NotNull
	private Integer quantity;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	private Orders orders;
	
	public OrderItem(@NotNull String productCode, @NotNull String productName,
			@NotNull Integer quantity) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
	}

	public OrderItem() {
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
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
