package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.OwnerWorkService;
import com.yh.service.impl.OwnerWorkServiceImpl;

import net.sf.json.JSONObject;

public class AssignmentAction extends HttpServlet{
	OwnerWorkService oService = new OwnerWorkServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		String owid = req.getParameter("OW_id");
		String rid = req.getParameter("pep");
		boolean ok = oService.assignment(Integer.parseInt(owid), Integer.parseInt(rid));
		System.out.println(owid);
		System.out.println(rid);
		JSONObject json = new JSONObject();
		json.put("bool", ok);
		pw.write(json.toString());
		
		
	}
}
