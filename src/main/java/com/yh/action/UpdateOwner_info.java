package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.Owner_infoService;
import com.yh.service.impl.Owner_infoServiceImpl;

import net.sf.json.JSONObject;

public class UpdateOwner_info extends HttpServlet{
	Owner_infoService os = new Owner_infoServiceImpl();
	  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	  @Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  req.setCharacterEncoding("utf8");
			resp.setCharacterEncoding("utf8");
		     String name = req.getParameter("name1");
		     String id = req.getParameter("id1");
		     String phone = req.getParameter("phone1");
		     String remark = req.getParameter("remark1");
		     int i = os.UpdateOwener(name, id, phone, remark);
		     PrintWriter pw = resp.getWriter();
		     JSONObject json = new JSONObject();
		     json.put("state", i);
		     pw.write(json.toString());
		     pw.flush();
		     pw.close();
		}
	}

