package com.yh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		String uri = ((HttpServletRequest) req).getServletPath();
		int end = uri.lastIndexOf("/");
		if (uri.endsWith(".jsp") || end > uri.lastIndexOf(".")) {
			String url = uri.substring(end + 1, uri.length());
			// 登录系列界面
			if (url.contains("login")||url.contains("getAllRoles")) {
				chain.doFilter(req, resp);
			} else {
				// 其他界面
				HttpSession session = httpReq.getSession();
				if (session.getAttribute("user") != null) {
					chain.doFilter(req, resp);
				} else {
					req.setAttribute("loginMessage", "未登录");
					httpResp.sendRedirect("/yang/login.jsp");
				}
			}
		} else {
			//System.out.println("通过过滤：" + uri);
			chain.doFilter(req, resp);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
