package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Complaint;

import net.sf.json.JSONObject;

public class SubmitManagerComplaint extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");

		String owner = req.getParameter("owner");
		String cstarttime = req.getParameter("cstarttime");
		String cendtime = req.getParameter("cendtime");
		//System.out.println(owner + cstarttime + cendtime);

		if ("".equals(owner)) 
		{
			Complaint c = new Complaint();
			c.setCstarttime(cstarttime);
			c.setCendtime(cendtime);
			c.setOid(0);
			
			HttpSession ses =req.getSession();
			ses.setAttribute("ComplaintByOid", c);
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
		}else
		{
			Complaint c = new Complaint();
			c.setCstarttime(cstarttime);
			c.setCendtime(cendtime);
			c.setOid(Integer.parseInt(owner));
			
			HttpSession ses =req.getSession();
			ses.setAttribute("ComplaintByOid", c);
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
}
