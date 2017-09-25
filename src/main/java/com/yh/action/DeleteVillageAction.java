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

public class DeleteVillageAction extends HttpServlet{
	VillageFacilityInfoServer villageServer = new VillageFacilityInfoServerImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		String[] svno = req.getParameterValues("vno[]");
		if(svno != null){
			int[] vnos = new int[svno.length];
			for (int i = 0; i < svno.length; i++) {
				vnos[i] = Integer.parseInt(svno[i]);
			}
			int bool = villageServer.deleteVollage(vnos);
			json.put("len", bool);
		}
		else{
			json.put("len", 0);
		}
		pw.write(json.toString());
		pw.flush();
		pw.close();
		
	}
}
