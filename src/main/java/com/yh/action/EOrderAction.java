package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.Account;
import com.yh.service.OwnerWorkService;
import com.yh.service.impl.OwnerWorkServiceImpl;

import net.sf.json.JSONObject;

public class EOrderAction extends HttpServlet{
	OwnerWorkService oService = new OwnerWorkServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		String spage = req.getParameter("page");
		String spageSize = req.getParameter("rows");
		String state = req.getParameter("state");
		int page = Integer.parseInt(spage);
		int pageSize = Integer.parseInt(spageSize);
		JSONObject json = oService.selectAllOrder(state ,page, pageSize);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		
	}
}
