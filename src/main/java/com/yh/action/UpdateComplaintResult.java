package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.Complaint;
import com.yh.service.ComplaintService;
import com.yh.service.impl.ComplaintServiceImple;

import net.sf.json.JSONObject;

public class UpdateComplaintResult extends HttpServlet{
	ComplaintService cs=new ComplaintServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		String cid = req.getParameter("cid2");
		String cremark=req.getParameter("cremark2");
		String cresult=req.getParameter("cresult2");
		
		Complaint c=new Complaint();
		c.setCid(Integer.parseInt(cid));
		c.setCremark(cremark);
		c.setCresult(cresult);
		
		boolean flag=cs.submitResultByObj(c);
		JSONObject j=new JSONObject();
		PrintWriter pw = resp.getWriter();
		if(flag==true){
			  j.put("success", true);
			  j.put("msg","反馈成功");
			  pw.write(j.toString());
		  }else{
			  j.put("success", false);
			  j.put("msg","反馈失败");
			  pw.write(j.toString());
		  }
	}
}
