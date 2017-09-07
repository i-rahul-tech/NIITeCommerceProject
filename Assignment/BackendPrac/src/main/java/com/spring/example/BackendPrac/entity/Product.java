package com.spring.example.BackendPrac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Product")

public class Product {

	@Id
	private int product_id;
	
	@Column(name="Product_Name")
	private String product_name;
	
	@Column(name="Price")
	private int price;

	@Column(name="Description")
	private String prod_description;
	
	public int getProductId() {
		return product_id;
	}

	public void setProductId(int id) {
		this.product_id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProd_description() {
		return prod_description;
	}

	public void setProd_description(String prod_description) {
		this.prod_description = prod_description;
	}
	
	
}
