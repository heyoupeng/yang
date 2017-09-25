package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.Property_infoService;
import com.yh.service.impl.Property_infoServiceImpl;

import net.sf.json.JSONObject;

public class DeleteProperty_info extends HttpServlet {
	Property_infoService ps = new Property_infoServiceImpl();
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
}
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("utf8");
	    resp.setCharacterEncoding("utf8");
	    int s = 0;
		 String checks[] = req.getParameterValues("ids[]");
		 for (int i = 0; i < checks.length; i++) {
			String msg[] =  checks[i].split(",");
			s = ps.deleteProperty_info(Integer.parseInt(msg[0]), Integer.parseInt(msg[1])
					, Integer.parseInt(msg[2]), Integer.parseInt(msg[3]));
		}
		 JSONObject json = new JSONObject();
		 json.put("state", s);
		 PrintWriter pw = resp.getWriter();
		 pw.write(json.toString());
		 pw.close();
		 pw.flush();
	}
}
