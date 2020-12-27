package com.example.training.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.CartItem;
import com.example.training.common.domain.Product;

import org.junit.jupiter.api.Test;

class CartTest {

	@Test
	void create() {
		Cart cart = new Cart();
		assertNotNull(cart);
	}

	@Test
	void add() {
		Product product = new Product(1, "ガム");
		Cart cart = new Cart();
		cart.add(product);
		assertEquals(1, cart.getSize());
		cart.add(product);
		assertEquals(1, cart.getSize());
	}

	@Test
	void addWithQuatity() {
		Product product = new Product(1, "ガム");
		Cart cart = new Cart();
		cart.add(product, 2);
		assertEquals(1, cart.getSize());
		cart.add(product, 3);
		assertEquals(1, cart.getSize());
		cart.add(product, 0);
		assertEquals(1, cart.getSize());
	}

	@Test
	void remove() {
		Product product = new Product(1, "ガム");
		Cart cart = new Cart();
		cart.add(product);
		assertEquals(1, cart.getSize());
		cart.remove(product);
		assertEquals(0, cart.getSize());
		cart.remove(product);
		assertEquals(0, cart.getSize());
	}

	@Test
	void list() {
		Product product = new Product(1, "ガム");
		Product product2 = new Product(2, "チョコ");
		Cart cart = new Cart();
		cart.add(product);

		List<CartItem> products = cart.getItems();
		assertNotNull(products);
		assertEquals(1, products.size());

		cart.add(product2);
		List<CartItem> products2 = cart.getItems();
		assertEquals(2, products2.size());

		cart.add(product);
		cart.add(product);
		Optional<CartItem> itemOpt = cart.getItem(product);
		assertTrue(itemOpt.isPresent());
		CartItem item = itemOpt.get();
		assertEquals(3, item.getQuantity());
		cart.add(product, 3);
		assertEquals(6, item.getQuantity());
	}

	@Test
	void totalAmound() {
		Product product1 = new Product(1, "ガム", 20);
		Product product2 = new Product(2, "チョコ", 100);
		Cart cart = new Cart();
		cart.add(product1);
		assertEquals(20, cart.getTotalAmount());
		cart.add(product1);
		cart.add(product1);
		assertEquals(60, cart.getTotalAmount());
		cart.add(product2);
		assertEquals(160, cart.getTotalAmount());
	}
}
