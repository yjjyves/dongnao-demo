package com.dn.jta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dn.jta.dao.db2.LogDao;
import com.dn.jta.model.Log;

// 本类内方法指定使用缓存时，默认的名称就是userCache
@Service
public class LogService {

	@Autowired
	private LogDao logDao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertLog(Log log) {
		this.logDao.insert(log);
	}

}
