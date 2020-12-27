
package com.example.training.member.controller;

import javax.servlet.http.HttpSession;

import com.example.training.member.Service.MemberService;
import com.example.training.member.domain.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

  String redirect = "redirect:/member";

  @Autowired
  protected HttpSession session;

  @Autowired
  private MemberService memberService;

  /**
   * 会員のログインページを表示する
   */
  @GetMapping("/login")
  public String login() {
    if (session.getAttribute(Member.SESSION_NAME) == null) {
      return "member/login";
    } else {
      return "redirect:/";
    }
  }

  /**
   * 会員登録ページを表示する
   */
  @GetMapping("applicate")
  public String applicate() {
    return "member/applicate";
  }

  /**
   * 会員を新規登録する
   */
  @PostMapping("/applicate")
  public String checkMemberInfo(@ModelAttribute("member") Member member) {
    memberService.create(member);
    return "redirect:/member/login";
  }
}
