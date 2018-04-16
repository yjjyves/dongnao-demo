package com.dongnao.sso.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongnao.sso.demo.Contants;
import com.dongnao.sso.demo.entity.User;
import com.dongnao.sso.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/doLogin")
	public String doLogin(String username, String pwd, HttpSession session,
			Model model) {
		// 校验用户名密码
		User user = this.userService.checkUsernamePwd(username, pwd);
		if (user == null) {
			model.addAttribute("error", "用户名或密码错误！");
			return "login";
		}
		// 认证成功，将用户信息放入session，后续需要知道这个会话是谁的时，就从会话里面去拿出来看下。
		session.setAttribute(Contants.CURR_USER, user);

		// 可能还有些别的事情要干，此处省略很多行代码

		// 重定向到首页
		return "redirect:index";
	}

	@RequestMapping("/index")
	public String toMain() {
		return "main";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

}
