package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.RepairService;
import com.yh.service.impl.RepairServiceImpl;

import net.sf.json.JSONObject;

public class DeleteRepairs extends HttpServlet{
	RepairService rs=new RepairServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		String no[]=req.getParameterValues("no[]");
		JSONObject obj=new JSONObject();
		if(rs.deleteRepairs(no)){
			obj.put("state", "200");//成功删除
		}else{
			obj.put("state", "400");//删除失败
		}
		PrintWriter pw=resp.getWriter();
		pw.write(obj.toString());	
	}
}
