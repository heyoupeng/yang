package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.QuitWork_infoService;
import com.yh.service.impl.QuitWork_infoServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShowQuitWork extends HttpServlet{
	QuitWork_infoService qs = new QuitWork_infoServiceImpl();
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
}
   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     req.setCharacterEncoding("utf8");
	     resp.setCharacterEncoding("utf8");
		 String rows = req.getParameter("rows");
		 String page = req.getParameter("page");
		 JSONObject json = qs.getLimitQuitWork_info(page, rows);
		 PrintWriter pw = resp.getWriter();
		 pw.write(json.toString());
		 pw.flush();
		 pw.close();
	}
}
