package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.ComplaintService;
import com.yh.service.impl.ComplaintServiceImple;

import net.sf.json.JSONObject;

public class AcceptComplaints extends HttpServlet{
	ComplaintService cs=new ComplaintServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		String[] cids=req.getParameterValues("cids[]");
		int [] cid=new int[cids.length];
		boolean flag=false;
		for (int i = 0; i < cids.length; i++) {
			cid[i]=Integer.parseInt(cids[i]);
			flag=cs.acceptComplaintsByCid(cid[i]);
		}
		JSONObject j=new JSONObject();
		PrintWriter pw = resp.getWriter();
		if(flag==true){
			  j.put("success", true);
			  j.put("msg","受理成功");
			  pw.write(j.toString());
		  }else{
			  j.put("success", false);
			  j.put("msg","受理失败");
			  pw.write(j.toString());
		  }
	}
}
