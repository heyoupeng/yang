package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.VillageFacilityInfo;
import com.yh.service.VillageFacilityInfoServer;
import com.yh.service.impl.VillageFacilityInfoServerImpl;

import net.sf.json.JSONObject;

public class UpdateVillageAction extends HttpServlet{
	VillageFacilityInfoServer vServer = new VillageFacilityInfoServerImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		String vNo = req.getParameter("vNo");
		String vName = req.getParameter("vName");
		String vNumber = req.getParameter("vNumber");
		String vRemark = req.getParameter("vRemark");
		String typeName = req.getParameter("typeName");
		VillageFacilityInfo vinfo = new VillageFacilityInfo(vName, Integer.parseInt(vNumber), vRemark, typeName);
		vinfo.setvNo(Integer.parseInt(vNo));
		boolean ok = vServer.updateVollage(vinfo);
		json.put("bool", ok);
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}
}
