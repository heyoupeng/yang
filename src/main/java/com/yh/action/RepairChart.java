package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.RepairChartService;
import com.yh.service.impl.RepairChartServiceImpl;

import net.sf.json.JSONObject;

public class RepairChart extends HttpServlet{
	RepairChartService rs = new RepairChartServiceImpl();
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
}
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  req.setCharacterEncoding("utf8");
		  resp.setCharacterEncoding("utf8");
		  String year = req.getParameter("year");
		  JSONObject json  = rs.getRepairChart(year);
          PrintWriter pw = resp.getWriter();
          pw.write(json.toString());
          pw.flush();
          pw.close();
	}
}
