package com.example.training.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.OrderAssemblerService;
import com.example.training.common.domain.OrderMonth;
import com.example.training.common.domain.Product;

public class OrderTest {

	@Test
	void orderSave() {
		Product product = new Product(1, "ガム", 20);
		Cart cart = new Cart();
		cart.add(product);

	}

//	@Autowired
//	private OrderAssemblerService service;

	@Test
	void orderMonth() {
		OrderAssemblerService service = new OrderAssemblerService();
		List<OrderMonth> orders = new ArrayList<>();
		OrderMonth order1 = new OrderMonth(1, LocalDate.of(2020, 01, 22));
		OrderMonth order2 = new OrderMonth(2, LocalDate.of(2020, 01, 12));
		OrderMonth order3 = new OrderMonth(3, LocalDate.of(2020, 12, 25));
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		Map<Integer, List<OrderMonth>> result = service.create(orders);
		System.out.println(result);
		assertThat(result.size(), is(2));
		assertThat(result, IsMapContaining.hasKey("1"));
		assertThat(result, IsMapContaining.hasKey("12"));

	}
}
