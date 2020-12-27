package com.example.training.common.domain;

public class CartItem {
	private Product product;
	private int quantity = 0;

	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1;
	}

	public int getProductId() {
		return product.getId();
	}

	public String getProductImage() {
		return product.getImage_path();
	}

	public String getProductName() {
		return product.getName();
	}

	public int getProductPrice() {
		return product.getPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int quantity) {
		this.quantity = this.quantity + quantity;
	}

	public void removeQuantity(int quantity) {
		this.quantity = this.quantity - quantity;
	}

	public void removeAll() {
		this.quantity = 0;
	}

	public boolean isEmpty() {
		return this.quantity <= 0;
	}

	public int getTotalAmount() {
		int price = this.getProductPrice() * this.getQuantity();
		return price;
	}
}
