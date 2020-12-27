
package com.example.training.common.domain;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class OrderForm {

	@NotEmpty
	@Size(min = 1, max = 6, message = "1文字以上、6文字以内で入力して下さい。")
	private String lastName;
	@NotEmpty
	@Size(min = 1, max = 6, message = "1文字以上、6文字以内で入力して下さい。")
	private String firstName;
	@NotEmpty
	@Email
	private String email;
	@Size(min = 10, max = 11, message = "正しく入力してください。")
	@Pattern(regexp = "[0-9]*", message = "半角数字のみで入力してください。(ハイフンなし)")
	private String phone;
	@NotEmpty
	@Size(max = 100, message = "長すぎます。")
	private String address1;// 都道府県
	@NotEmpty
	@Size(max = 100, message = "長すぎます。")
	private String address2;// 番地
	@NotNull
	private int memberId;

	private LocalDate dateNow = LocalDate.now();

	public String getFullName() {
		return this.lastName + this.firstName;
	}

	public String getFullAddress() {
		return this.address1 + this.address2;
	}

	public Order createOrder() {
		return new Order(this);
	}

}
