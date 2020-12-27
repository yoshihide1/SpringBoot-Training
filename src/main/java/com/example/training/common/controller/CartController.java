package com.example.training.common.controller;

import javax.servlet.http.HttpSession;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.Product;
import com.example.training.common.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tsukamoto
 *
 */
@Controller
@RequestMapping("/member/cart")
public class CartController {
	// private Cart cart = new Cart();
	@Autowired
	protected HttpSession session;

	@Autowired
	private ProductRepository productRepository;

	/**
	 * @param id
	 * @return カートに商品の追加
	 */
	@GetMapping("/add/{id}")
	public String add(@PathVariable int id) {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		Product product = productRepository.findId(id).orElseThrow();
		cart.add(product);
		return "redirect:/";
	}

	/**
	 * @param id
	 * @return カート内の商品の削除
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		Product product = productRepository.findId(id).orElseThrow();
		cart.remove(product);

		return "redirect:/member/cart/list";
	}

	/**
	 * 商品一覧画面の表示
	 *
	 */
	@GetMapping("/list")
	public String doGet(Model model) {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		model.addAttribute("cart", cart);
		return "member/cart";
	}

	@GetMapping("/clear")
	public String clear() {
		session.removeAttribute("cart");
		return "redirect:/member/cart";
	}

}
