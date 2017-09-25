package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.ManagerService;
import com.yh.service.impl.ManagerServiceImple;

import net.sf.json.JSONArray;

public class ManagerNo extends HttpServlet{
	ManagerService ms=new ManagerServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		
		JSONArray j=ms.getAllManagerNo();
		//回写数据
		PrintWriter  pw =  resp.getWriter();//写回
		//System.out.println(j.toString());
		pw.write(j.toString());
	}
}
