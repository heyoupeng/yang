package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Complaint;
import com.yh.model.Estate;
import com.yh.service.ComplaintService;
import com.yh.service.impl.ComplaintServiceImple;

import net.sf.json.JSONObject;

public class SelectOwnerComplaint extends HttpServlet{
	ComplaintService cs=new ComplaintServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		
		int  page =  Integer.parseInt(req.getParameter("page"));
		int  pageSize = Integer.parseInt(req.getParameter("rows"));
		
		HttpSession se = req.getSession();
		Complaint c = (Complaint) se.getAttribute("Complaint");
		//System.out.println(c.getMno()+"-"+c.getCstarttime());
		
		List<Complaint> complaints = cs.searchComplaintByObj(c,page,pageSize);
		int count = complaints.size();
		// 拼接写回，一定的格式不能变
		JSONObject j = new JSONObject();
		j.put("total", count);// 获得总数
		j.put("rows", complaints);// 获得对象数组
		//System.out.println(j.toString());
		PrintWriter pw = resp.getWriter();// 写回
		pw.write(j.toString());
		
	}
}
