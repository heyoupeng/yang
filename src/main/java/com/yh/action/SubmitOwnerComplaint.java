package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Account;
import com.yh.model.Complaint;
import com.yh.model.Estate;
import com.yh.service.OwnerService;
import com.yh.service.impl.OwnerServiceImple;

import net.sf.json.JSONObject;

public class SubmitOwnerComplaint extends HttpServlet{
	OwnerService os=new OwnerServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		
		String mno = req.getParameter("mno");
		String cstarttime = req.getParameter("cstarttime");
		//System.out.println(role);
		
		HttpSession session =req.getSession();
		Account acc=(Account) session.getAttribute("user");
		//通过账户的id找到Owner的oid
		int oid=os.searchOidByAccountID(acc.getUsername());
		if("".equals(mno))
		{
			Complaint c=new Complaint();
			c.setCstarttime(cstarttime);
			c.setOid(oid);
			
			HttpSession ses =req.getSession();
			ses.setAttribute("Complaint", c);
			
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
			Complaint c=new Complaint();
			c.setMno(Integer.parseInt(mno));
			c.setCstarttime(cstarttime);
			c.setOid(oid);
			
			HttpSession ses =req.getSession();
			ses.setAttribute("Complaint", c);
			
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
