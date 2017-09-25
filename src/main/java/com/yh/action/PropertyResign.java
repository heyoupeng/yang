package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Account;
import com.yh.service.QuitWork_infoService;
import com.yh.service.impl.QuitWork_infoServiceImpl;

import net.sf.json.JSONObject;

public class PropertyResign extends HttpServlet {
	QuitWork_infoService qs = new QuitWork_infoServiceImpl();
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
}
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf8");
		 resp.setCharacterEncoding("utf8");
		 String content = req.getParameter("content");
		 HttpSession session = req.getSession();
		 Account acc =(Account)session.getAttribute("user");
		 int i = qs.propertyInsertQuitWork_info(acc.getUsername(), content);
		 JSONObject json = new JSONObject();
		 json.put("state", i);
		 PrintWriter pw = resp.getWriter();
		 pw.write(json.toString());
		 pw.flush();
		 pw.close();
		 }
}
