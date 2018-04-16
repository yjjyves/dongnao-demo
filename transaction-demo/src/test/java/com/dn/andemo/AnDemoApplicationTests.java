package com.dn.andemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dn.andemo.model.User;
import com.dn.andemo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnDemoApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void testAddUser() throws Exception {
		User user = new User();
		user.setId("x002");
		user.setUserName("zhangsan");

		this.userService.insertUser(user);

	}

}
