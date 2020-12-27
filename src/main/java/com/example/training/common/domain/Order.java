package com.example.training.common.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Order {

	private int id;
	private int memberId;
	private String name;
	private String address;
	private String email;
	private String phone;
	private int price;
	private LocalDate date;

	public Order(OrderForm orderForm) {
		this.memberId = orderForm.getMemberId();
		this.name = orderForm.getFullName();
		this.address = orderForm.getFullAddress();
		this.email = orderForm.getEmail();
		this.phone = orderForm.getPhone();
		this.date = LocalDate.now();
	}

	public Order(int orderId, int memberId, LocalDate date) {
		this.id = orderId;
		this.memberId = memberId;
		this.date = date;
	}

	public Order() {
	}

	public List<OrderItem> createItems(Cart cart) {
		List<OrderItem> results = new ArrayList<OrderItem>(cart.getSize());
		for (CartItem item : cart.getItems()) {
			results.add(new OrderItem(item));
		}
		return results;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setPrice(Cart cart) {
		int total = 0;
		for (CartItem item : cart.getItems()) {
			total += item.getTotalAmount();
		}
		this.price = total;
	}
}
