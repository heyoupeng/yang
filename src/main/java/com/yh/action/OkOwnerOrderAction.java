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

public class OkOwnerOrderAction extends HttpServlet{
	OwnerWorkService oService = new OwnerWorkServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		String owid = req.getParameter("OW_id");
		String ow_result = req.getParameter("ow_result");
		if(oService.okOrder(Integer.parseInt(owid), ow_result) > 0){
			json.put("bool", true);
		}
		else{
			json.put("bool", false);
		}
		pw.write(json.toString());
		pw.flush();
		pw.close();
		
	}
}

