package com.dn.andemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dn.andemo.dao.LogDao;
import com.dn.andemo.model.Log;

// 本类内方法指定使用缓存时，默认的名称就是userCache
@Service
public class LogService {

	@Autowired
	private LogDao logDao;

	@Autowired
	private DataSourceTransactionManager txManager;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertLog(Log log) {
		this.logDao.insert(log);
	}

}
