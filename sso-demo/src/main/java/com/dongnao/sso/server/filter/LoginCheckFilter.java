package com.dongnao.sso.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.sso.client.Config;
import com.dongnao.sso.demo.Contants;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(urlPatterns = "/index", filterName = "loginCheckFilter")
public class LoginCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 判断会话中是否有user对象，有，认证过的；无，没有认证的陌生人
		if (req.getSession().getAttribute(Contants.CURR_USER) != null) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(Config.getConfig(Config.SSO_SERVER_LOGIN_URL));
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
