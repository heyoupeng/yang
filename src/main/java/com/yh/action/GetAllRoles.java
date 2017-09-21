package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.RoleService;
import com.yh.service.impl.RoleServiceImpl;

import net.sf.json.JSONArray;

public class GetAllRoles extends HttpServlet{
	RoleService roleService=new RoleServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf8");
		PrintWriter pw=resp.getWriter();
		JSONArray roles=roleService.getAllRoles();
		pw.write(roles.toString());
	}
}
