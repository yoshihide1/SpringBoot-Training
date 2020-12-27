package com.example.training.member.auth;

import java.util.Optional;

import com.example.training.member.domain.Member;
import com.example.training.member.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("LoginMemberDetailsService")
public class LoginMemberDetailsService implements UserDetailsService {
	@Autowired
	MemberRepository memberRepository;

	/**
	 * メールアドレスで検索したユーザーのuserエンティティをLoginUserDetailsクラスのインスタンスへ変換する
	 *
	 * @param email 検索するユーザーのメールアドレス
	 * @return メールアドレスで検索できたユーザーのユーザー情報
	 * @throws UsernameNotFoundException メールアドレスでユーザーが検索できなかった場合にスローする。
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		assert (email != null);
		log.debug("loadUserByUsername(email):[{}]", email);
		Optional<Member> memberOpt = memberRepository.findByEmail(email);
		if (memberOpt.isEmpty()) {
			throw new UsernameNotFoundException("User not found for email: " + email);
		} else {
			Member member = memberOpt.get();
			if (member.getStatus().equals("unapproved")) {
				throw new UsernameNotFoundException("Unauthorized user.: " + email);
			} else {
				return new LoginMemberDetails(member);
			}
		}
		// return memberRepository.findByEmail(email).map(LoginMemberDetails::new)
		// .orElseThrow(() -> new UsernameNotFoundException("User not found by email:["
		// + email + "]"));
	}
}
