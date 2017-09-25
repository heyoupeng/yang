package com.yh.action;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yh.model.Estate;
import com.yh.service.EstatePersonService;
import com.yh.service.impl.EstatePersonServiceImple;

import net.sf.json.JSONObject;

public class UpdateEstatePerson extends HttpServlet {
	EstatePersonService eps=new EstatePersonServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		int eno=Integer.parseInt(req.getParameter("eno2"));
		String ename = req.getParameter("uname2");
		String ephone = req.getParameter("uphone2");
		String eid = req.getParameter("uid2");
		 System.out.println(eno+"-"+ename+"-"+ephone+"-"+eid);

		// 将参数一模型方式传输
		Estate e = new Estate();
		e.setEno(eno);
		e.setEname(ename);
		e.setEphone(ephone);
		e.setEid(eid);

		// 更新数据库内容
		boolean flag = eps.changeEstatePersonByObj(e);
		// 拼接写回，一定的格式不能变
		JSONObject j = new JSONObject();
		PrintWriter pw = resp.getWriter();
		if (flag) {
			j.put("success", true);
			j.put("msg", "添加成功");
			pw.write(j.toString());
		} else {
			j.put("success", false);
			j.put("msg", "添加失败");
			pw.write(j.toString());
		}
	}
}
