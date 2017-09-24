package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.RepairService;
import com.yh.service.impl.RepairServiceImpl;

import net.sf.json.JSONObject;

public class GetDatagridRepairs extends HttpServlet{
	RepairService rs=new RepairServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取信息的页数
		int thisPage=Integer.parseInt(req.getParameter("page"));
		// 每页信息数
		int numOfPage = Integer.parseInt(req.getParameter("rows"));
		// 起始信息位置
		int start = (thisPage - 1) * numOfPage;
		// 回传相应信息
		JSONObject json=rs.getDatagridRepairs(start, numOfPage);
		resp.setCharacterEncoding("utf8");
		PrintWriter writer=resp.getWriter();
		writer.write(json.toString());
	}
}