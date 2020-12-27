package com.example.training.common.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderMonth {
	private int orderId;
	private LocalDate date;

	public OrderMonth(int orderId, LocalDate date) {
		this.orderId = orderId;
		this.date = date;
	}

	public OrderMonth() {
	}
}
