package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Estate;
import com.yh.service.EstatePersonService;
import com.yh.service.impl.EstatePersonServiceImple;

import net.sf.json.JSONObject;

public class SelectEstatePerson extends HttpServlet {
	EstatePersonService eps = new EstatePersonServiceImple();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		// String ename = req.getParameter("ename");
		// String ephone = req.getParameter("ephone");
		// String eid = req.getParameter("eid");
		// String estarttime = req.getParameter("estarttime");
		// // String eendtime=req.getParameter("eendtime");
		// System.out.println(ename + " " + ephone + " " + eid + " " +
		// estarttime + " /");
		//
		// // 将参数一模型方式传输
		// Estate e = new Estate();
		// e.setEname(ename);
		// e.setEphone(ephone);
		// e.setEid(eid);
		// e.setCurrentTime(estarttime);
		//
		int  page =  Integer.parseInt(req.getParameter("page"));
		int  pageSize = Integer.parseInt(req.getParameter("rows"));
		//System.out.println(page+" "+pageSize);
		
		HttpSession se = req.getSession();
		Estate e = (Estate) se.getAttribute("Estate");

		List<Estate> Estates = eps.searchEstatePersonByObj(e,page,pageSize);
		int count = Estates.size();
		// 拼接写回，一定的格式不能变
		JSONObject j = new JSONObject();
		j.put("total", count);// 获得总数
		j.put("rows", Estates);// 获得对象数组
		PrintWriter pw = resp.getWriter();// 写回
		pw.write(j.toString());
		//System.out.println(j.toString());

	}
}
