package com.dn.jta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dn.jta.dao.db1.UserDao;
import com.dn.jta.dao.db2.LogDao;
import com.dn.jta.model.Log;
import com.dn.jta.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

// 本类内方法指定使用缓存时，默认的名称就是userCache
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private LogDao logDao;

	@Autowired
	private LogService logService;

	@Transactional
	public User insertUser(User u) {
		this.userDao.insert(u);

		System.out.println(
				(System.currentTimeMillis() + "-" + u.getUserName()).length());
		Log log = new Log(System.currentTimeMillis() + "",
				System.currentTimeMillis() + "-" + u.getUserName());

		this.logDao.insert(log);

		return this.userDao.find(u.getId());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void doAddUser(User u) {
		this.userDao.insert(u);
	}

	public User updateUser(User u) {
		this.userDao.update(u);
		return this.userDao.find(u.getId());
	}

	public User findById(String id) {
		System.err.println("根据id=" + id + "获取用户对象，从数据库中获取");
		return this.userDao.find(id);
	}

	public void deleteById(String id) {
		this.userDao.delete(id);
	}

	public PageInfo<User> queryPage(String userName, int pageNum,
			int pageSize) {
		Page<User> page = PageHelper.startPage(pageNum, pageSize);
		// PageHelper会自动拦截到下面这查询sql
		this.userDao.query(userName);
		return page.toPageInfo();
	}

}
