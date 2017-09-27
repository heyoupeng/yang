package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.ComplaintChartService;
import com.yh.service.impl.ComplaintChartServiceImpl;

import net.sf.json.JSONObject;
/**
 * 投诉报表生成
 * @author ccy
 *
 */
public class ComplaintChart extends HttpServlet {
	ComplaintChartService cs = new ComplaintChartServiceImpl();
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
}
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf8");
		 resp.setCharacterEncoding("utf8");
		 String year = req.getParameter("year"); 
		 JSONObject json = cs.getComplaintChart(year);
		 PrintWriter pw = resp.getWriter();
		 pw.write(json.toString());
		 pw.flush();
		 pw.close();
	}
}
