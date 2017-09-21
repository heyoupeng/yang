package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.FunctionService;
import com.yh.service.impl.FunctionServiceImpl;

import net.sf.json.JSONArray;

public class GetRoleFunctions extends HttpServlet {
	FunctionService functionService = new FunctionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rid = req.getParameter("rid");
		if (rid == null) {

		} else {
			JSONArray array = functionService.getRoleFunctions(rid);
			resp.setCharacterEncoding("utf8");
			PrintWriter pw = resp.getWriter();
			pw.write(array.toString());
		}
	}
}
