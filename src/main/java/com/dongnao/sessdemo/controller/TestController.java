package com.dongnao.sessdemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/sess")
	@ResponseBody
	public String sessionTrack(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("u", "aaa");
		return "hello worldsssssssssssssaaaaaaaaaaaaaaaa";
	}
}
