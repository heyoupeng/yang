package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;

import com.yh.service.Property_infoService;
import com.yh.service.impl.Property_infoServiceImpl;

import net.sf.json.JSONObject;

public class UpdateProperty_info extends HttpServlet {
	Property_infoService ps = new Property_infoServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		String build = req.getParameter("build1");
		String unit = req.getParameter("unit1");
		String floor = req.getParameter("floor1");
		String room = req.getParameter("room1");
		String ownerId = req.getParameter("ownerId1");
		String area = req.getParameter("area1");
		String remark = req.getParameter("remark1");
		PrintWriter pw = resp.getWriter();
		if (isDouble(area)) {
              int i = ps.updateProperty_info(Integer.parseInt(build),
            		  Integer.parseInt(unit), Integer.parseInt(floor),Integer.parseInt(room),
            		  Double.parseDouble(area),Integer.parseInt(ownerId), remark);
             JSONObject json = new JSONObject();
  			json.put("state", i);
  			pw.print(json.toString());
		} else {
			JSONObject json = new JSONObject();
			json.put("state", 2);
			pw.print(json.toString());
		}
		pw.flush();
		pw.close();
	}

	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException ex) {
		}
		return false;
	}
}
