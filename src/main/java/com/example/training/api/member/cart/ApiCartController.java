package com.example.training.api.member.cart;

import javax.servlet.http.HttpSession;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.Product;
import com.example.training.common.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/member/cart")
public class ApiCartController {

	@Autowired
	private HttpSession session;

	@Autowired
	private ProductRepository productRepository;

	/**
	 *
	 * カートに商品の追加する
	 *
	 * @return
	 */
	@PostMapping("/add/{productId}")
	public void add(@PathVariable int productId) {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		Product product = productRepository.findId(productId).orElseThrow();
		cart.add(product);
	}

	/**
	 *
	 * カートから特定の商品をすべて削除する
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{productId}")
	public void cartFromParticularProductsAllDelete(@PathVariable int productId) {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		Product product = productRepository.findId(productId).orElseThrow();
		cart.removeAll(product);
	}

	/**
	 *
	 * カート内の商品を返す
	 *
	 * @return
	 */
	@GetMapping("/list")
	public Object list() {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		return cart;
	}

	/**
	 *
	 * カートに商品がないか確認する
	 *
	 * @return
	 */
	@GetMapping("/hasItem")
	public Boolean cartHasItem() {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		if (0 < cart.getSize()) {
			return true;
		} else {
			return false;
		}
	}
}
