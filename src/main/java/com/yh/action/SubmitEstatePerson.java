package com.yh.action;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Estate;

import net.sf.json.JSONObject;

public class SubmitEstatePerson extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		
		String ename = req.getParameter("ename");
		String ephone = req.getParameter("ephone");
		String eid = req.getParameter("eid");
		String estarttime = req.getParameter("estarttime");
		//System.out.println(role);
		
		Estate e = new Estate();
		e.setEname(ename);
		e.setEphone(ephone);
		e.setEid(eid);
		e.setCurrentTime(estarttime);
		
		HttpSession ses =req.getSession();
		ses.setAttribute("Estate", e);
		
		boolean flag=true;
		JSONObject j=new JSONObject();
		PrintWriter pw = resp.getWriter();
		if(flag==true){
			  j.put("success", true);
			  j.put("msg","查询成功");
			  pw.write(j.toString());
		  }else{
			  j.put("success", false);
			  j.put("msg","查询失败");
			  pw.write(j.toString());
		  }
	}
}
