package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.RepairService;
import com.yh.service.impl.RepairServiceImpl;

import net.sf.json.JSONArray;

public class GetReprAction extends HttpServlet{
	
	RepairService rservice = new RepairServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONArray json = rservice.getAllRair();
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}

}
