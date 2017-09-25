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

public class DelEstatePerson extends HttpServlet{
	EstatePersonService eps=new EstatePersonServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		System.out.println("time to delete..");
		String[]  enos= req.getParameterValues("enos[]");
		//删除
		boolean flag=false;
		boolean del=false;
		if(flag==false)
		{	
			
			for (String eno : enos) {
				//循环删除
				Estate e=new Estate();
				e.setEno(Integer.parseInt(eno));
				del = eps.deleteEstatePersonByObj(e);
			}
			flag=true;
		}else{
			flag=false;
		}
		JSONObject j1=new JSONObject();
		PrintWriter pw = resp.getWriter();
		if(del){
			  j1.put("success", true);
			  j1.put("msg","删除成功");
			  pw.write(j1.toString());
		  }else{
			  j1.put("success", false);
			  j1.put("msg","删除失败");
			  pw.write(j1.toString());
		  }
	}
}
