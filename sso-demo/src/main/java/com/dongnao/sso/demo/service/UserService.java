package com.dongnao.sso.demo.service;

import org.springframework.stereotype.Service;

import com.dongnao.sso.demo.entity.User;

@Service
public class UserService {

	public User checkUsernamePwd(String username, String pwd) {
		if ("admin".equals(username) && "admin".equals(pwd)) {
			User u = new User();
			u.setUsername(username);
			u.setId("u00001");
			return u;
		}

		return null;
	}
}
