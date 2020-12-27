package com.example.training.common.domain;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.common.repository.OrderRepository;
import com.example.training.member.domain.Member;

@Service
public class OrderService {
	public static final String SESSION_NAME = "ORDER_PERSON";

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private HttpSession session;

	public int order(@Valid OrderForm orderForm, Cart cart) {
		Member member = (Member) session.getAttribute(Member.SESSION_NAME);
		Order order = orderForm.createOrder();
		order.setMemberId(member.getId());
		order.setPrice(cart);
		orderRepository.save(order);
		List<OrderItem> items = order.createItems(cart);
		for (OrderItem item : items) {
			orderRepository.saveItem(item, order.getId());
		}
		return order.getId();
	}
}
