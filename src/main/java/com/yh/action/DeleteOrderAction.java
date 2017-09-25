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

public class DeleteOrderAction extends HttpServlet{
	OwnerWorkService oService = new OwnerWorkServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		String[] owids = req.getParameterValues("owids[]");
		if(owids.length > 0){
			int[] iowids = new int[owids.length];
			for(int i = 0 ; i < iowids.length ; i ++){
				iowids[i] = Integer.parseInt(owids[i]);
			}
			int sum = oService.deleteOrder(iowids);
			json.put("sum", sum);
		}
		else{
			json.put("sum", 0);
		}
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}
}
