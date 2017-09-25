package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.Owner_infoService;
import com.yh.service.impl.Owner_infoServiceImpl;

import net.sf.json.JSONArray;

public class ShowO_ownerId extends HttpServlet{
	Owner_infoService os = new Owner_infoServiceImpl();
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
}
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  resp.setCharacterEncoding("utf8");
      req.setCharacterEncoding("utf8");
	     JSONArray json = os.getO_ownerId();
	     PrintWriter pw = resp.getWriter();
	     pw.write(json.toString());
	     pw.flush();
	     pw.close();
	}
}
