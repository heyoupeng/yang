package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.Repair;
import com.yh.service.RepairService;
import com.yh.service.impl.RepairServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InsertRepair extends HttpServlet{
	RepairService rs=new RepairServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		Repair repair=new Repair();
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String phone=req.getParameter("phone");
		String startTime=req.getParameter("startTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date=new Date(sdf.parse(startTime).getTime());
			repair.setStartTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		repair.setName(name);
		repair.setId(id);
		repair.setPhone(phone);
		JSONObject obj=new JSONObject();
		if(rs.insertRepair(repair)){
			obj.put("state", "200");//表示成功
		}else{
			obj.put("state", "400");//表示失败
		}
		PrintWriter pw=resp.getWriter();
		pw.write(obj.toString());
	}
}
