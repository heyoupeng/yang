package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Account;
import com.yh.model.Complaint;
import com.yh.service.ComplaintService;
import com.yh.service.ManagerService;
import com.yh.service.impl.ComplaintServiceImple;
import com.yh.service.impl.ManagerServiceImple;

import net.sf.json.JSONObject;

public class SelectManagerComplaint extends HttpServlet{
	ManagerService ms=new ManagerServiceImple();
	ComplaintService cs=new ComplaintServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		int  page =  Integer.parseInt(req.getParameter("page"));
		int  pageSize = Integer.parseInt(req.getParameter("rows"));
		
		//获得登录的对象
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("user");
		
		//获得查询条件对象
		HttpSession sess = req.getSession();
		Complaint c=(Complaint) sess.getAttribute("ComplaintByOid");
		
		int mno = ms.getManageNoByName(acc.getUsername());
		//System.out.println(acc.getUsername()+"-"+mno);
		c.setMno(mno);
		//System.out.println(c.getOid()+c.getCstarttime()+c.getCendtime()+c.getMno());
		if(c.getOid()==0)
		{
			//查询投诉工单
			List<Complaint> complaints = cs.searchComplaintByMySelfAll(c,page,pageSize);
			int count = complaints.size();
			// 拼接写回，一定的格式不能变
			JSONObject j = new JSONObject();
			j.put("total", count);// 获得总数
			j.put("rows", complaints);// 获得对象数组
			PrintWriter pw = resp.getWriter();// 写回
			pw.write(j.toString());
			//System.out.println(j.toString());
		}else
		{
			//查询投诉工单
			List<Complaint> complaints = cs.searchComplaintByMySelf(c,page,pageSize);
			int count = complaints.size();
			// 拼接写回，一定的格式不能变
			JSONObject j = new JSONObject();
			j.put("total", count);// 获得总数
			j.put("rows", complaints);// 获得对象数组
			PrintWriter pw = resp.getWriter();// 写回
			pw.write(j.toString());
			//System.out.println(j.toString());
		}
		
	}
}
