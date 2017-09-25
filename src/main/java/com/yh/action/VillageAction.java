package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.VillageFacilityInfoServer;
import com.yh.service.impl.VillageFacilityInfoServerImpl;

import net.sf.json.JSONObject;

public class VillageAction extends HttpServlet{
	VillageFacilityInfoServer villageServer = new VillageFacilityInfoServerImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		String spage = req.getParameter("page");
		String spageSize = req.getParameter("rows");
		int page = Integer.parseInt(spage);
		int pageSize = Integer.parseInt(spageSize);
		JSONObject json = villageServer.selectVollageByPage(page, pageSize);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		
	}
}
