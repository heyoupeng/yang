package com.yh.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.Repair;
import com.yh.service.RepairService;
import com.yh.service.impl.RepairServiceImpl;

public class UpdateRepair extends HttpServlet{
	RepairService rs=new RepairServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		int no=Integer.parseInt(req.getParameter("no"));
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String phone=req.getParameter("phone");
		String startTime=req.getParameter("startTime");
		Repair repair=new Repair();
		repair.setNo(no);
		repair.setName(name);
		repair.setId(id);
		repair.setPhone(phone);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date=new Date(sdf.parse(startTime).getTime());
			repair.setStartTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(rs.updateRepair(repair)){
			
		}else{
			
		}
		
	}
}
