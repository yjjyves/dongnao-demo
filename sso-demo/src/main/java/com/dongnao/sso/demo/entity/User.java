package com.dongnao.sso.demo.entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = -3384345423422208234L;

	private String id;

	private String username;

	private String pwd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
