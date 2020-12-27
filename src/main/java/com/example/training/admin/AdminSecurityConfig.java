package com.example.training.admin;

import com.example.training.AdminSuccessHandler;
import com.example.training.admin.auth.LoginAdminDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

  // アカウント登録時のパスワードエンコードで利用するためDI管理する。
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private AdminSuccessHandler adminSuccessHandler;

  @Autowired
  @Qualifier("LoginAdminDetailsService")
  private LoginAdminDetailsService service;

  /**
   * セキュリティの対象から外す
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    StrictHttpFirewall firewall = new StrictHttpFirewall();
    firewall.setAllowSemicolon(true);
    web.httpFirewall(firewall);
    // @formatter:off
		web
			.ignoring()
			.mvcMatchers("/static/**", "/webjars/**", "/js/**") // 静的リソースに認証が行われないようにする。
		;
		// @formatter:on
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // @formatter:off
    http
      .mvcMatcher("/admin/**")
      .authorizeRequests()
        .mvcMatchers("/admin/login").permitAll() // 管理者用ログイン画面は誰でもアクセス可能
				.mvcMatchers("/admin/**").hasRole("ADMIN") // admin以下は ADMINロールを持つ認証ユーザのみアクセスできる。
        .anyRequest()
        .authenticated() // 上記以外は認証ユーザのみアクセスできる
			.and()
			.formLogin()
        .loginPage("/admin/login")
        .loginProcessingUrl("/admin/login")
				.usernameParameter("name")
        .passwordParameter("password")
        .defaultSuccessUrl("/admin")
        .successHandler(adminSuccessHandler)
			.and()
			.logout()
        .logoutUrl("/admin/logout")
        .logoutSuccessUrl("/")
        .deleteCookies("JSESSIONID")
				.invalidateHttpSession(true) // ログアウト時のセッション破棄を有効化
      .and()
        .csrf()
        .disable()
		;
		// @formatter:on
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
  }

}
