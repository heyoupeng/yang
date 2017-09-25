package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.Account;
import com.yh.model.OwnerWorkOrderInfo;
import com.yh.model.VillageFacilityInfo;
import com.yh.service.OwnerWorkService;
import com.yh.service.VillageFacilityInfoServer;
import com.yh.service.impl.OwnerWorkServiceImpl;
import com.yh.service.impl.VillageFacilityInfoServerImpl;

import net.sf.json.JSONObject;

public class AddOwnerOrderAction extends HttpServlet{
	OwnerWorkService oService = new OwnerWorkServiceImpl();
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
		Account ac = (Account)req.getSession().getAttribute("user");
		String aname = ac.getUsername();
		String remark = req.getParameter("remark");
		OwnerWorkOrderInfo oinfo = new OwnerWorkOrderInfo();
		oinfo.setOW_remark(remark);
		oinfo.setO_name(aname);
		boolean ok = oService.insertOrder(oinfo);
		json.put("bool", ok);
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}
}
