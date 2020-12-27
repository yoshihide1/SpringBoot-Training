package com.example.training;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.training.admin.domain.Admin;
import com.example.training.admin.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 認証成功時のハンドラ
 *
 * @author T.Harao
 *
 */
@Component
public class AdminSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  protected HttpSession session;

  @Autowired
  private AdminRepository adminRepository;

  /**
   * 認証成功時
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    System.out.println(authentication);
    // 認証したユーザのemail
    String name = authentication.getName();
    Optional<Admin> admin = adminRepository.findByName(name);
    if (admin.isEmpty()) {
    } else {
      // セッションにユーザ情報を格納する
      session.setAttribute(Admin.SESSION_NAME, admin.get());
      response.sendRedirect("/admin");
    }
  }

}
