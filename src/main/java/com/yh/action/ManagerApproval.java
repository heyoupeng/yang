package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.QuitWork_infoService;
import com.yh.service.impl.QuitWork_infoServiceImpl;

import net.sf.json.JSONObject;

public class ManagerApproval extends HttpServlet {
	QuitWork_infoService qs = new QuitWork_infoServiceImpl();
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  doPost(req, resp);
}
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("utf8");
	    resp.setCharacterEncoding("utf8");
	    String id = req.getParameter("id");
	    String result = req.getParameter("result");
	    String state = req.getParameter("dept");
	    int i = qs.ApprovalQuitWork_info(id, state,result);
	    JSONObject json = new JSONObject();
	    json.put("state", i);
	    PrintWriter pw = resp.getWriter();
	    pw.write(json.toString());
	    pw.flush();
	    pw.close();
 	}
}
