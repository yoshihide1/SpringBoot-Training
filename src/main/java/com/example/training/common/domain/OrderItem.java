package com.example.training.common.domain;

import lombok.Data;

@Data
public class OrderItem {
	private String name;
	private int price;
	private int quantity;
	private String imagePath;

	public OrderItem(CartItem item) {
		this.name = item.getProductName();
		this.price = item.getProductPrice();
		this.quantity = item.getQuantity();
		this.imagePath = item.getProductImage();
	}

	public OrderItem() {
	}

}
