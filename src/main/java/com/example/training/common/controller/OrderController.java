package com.example.training.common.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.Order;
import com.example.training.common.domain.OrderForm;
import com.example.training.common.domain.OrderItem;
import com.example.training.common.domain.OrderService;
import com.example.training.common.repository.OrderRepository;
import com.example.training.member.domain.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/order")
public class OrderController {

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	/**
	 * 注文詳細と住所入力フォームに遷移
	 */
	@GetMapping("/form")
	public String form(OrderForm orderForm, Model model) {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		Member member = (Member) session.getAttribute(Member.SESSION_NAME);
		orderForm.setMemberId(member.getId());
		model.addAttribute("cart", cart);
		model.addAttribute("orderForm", orderForm);
		return "member/order/detail";
	}

	/**
	 * 注文を処理する
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute("orderForm") @Valid OrderForm orderForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return form(orderForm, model);
		} else {
			Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
			int orderId = orderService.order(orderForm, cart);
			session.setAttribute(Cart.SESSION_NAME, new Cart());
			return "redirect:/member/order/complete/" + orderId;
		}
	}

	/**
	 * 注文明細画面
	 */
	@GetMapping("/complete/{id}")
	public String complete(@PathVariable int id, Model model) {
		Order order = orderRepository.findById(id);
		List<OrderItem> items = orderRepository.findItemsByOrder(order);
		model.addAttribute("order", order);
		model.addAttribute("items", items);
		return "member/order/complete";
	}
}
