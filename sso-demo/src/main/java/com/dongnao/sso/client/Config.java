package com.dongnao.sso.client;

import java.io.IOException;
import java.util.Properties;

public class Config {

	private static Properties properties = new Properties();

	static {
		try {
			properties
					.load(Config.class.getResourceAsStream("/sso.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getConfig(String name) {
		return properties.getProperty(name);
	}

	public static final String SSO_SERVER_LOGIN_URL = "sso.server.login.url";
}
