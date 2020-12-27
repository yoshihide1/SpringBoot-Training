package com.example.training.admin.auth;

import com.example.training.admin.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("LoginAdminDetailsService")
public class LoginAdminDetailsService implements UserDetailsService {
  @Autowired
  AdminRepository adminRepository;

  /**
   * メールアドレスで検索したユーザーのuserエンティティをLoginUserDetailsクラスのインスタンスへ変換する
   *
   * @param email 検索するユーザーのメールアドレス
   * @return メールアドレスで検索できたユーザーのユーザー情報
   * @throws UsernameNotFoundException メールアドレスでユーザーが検索できなかった場合にスローする。
   */
  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    assert (name != null);
    log.debug("loadUserByUsername(email):[{}]", name);
    return adminRepository.findByName(name).map(LoginAdminDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found by email:[" + name + "]"));
  }
}
