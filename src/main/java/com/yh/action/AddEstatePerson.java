package com.yh.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.Estate;
import com.yh.service.EstatePersonService;
import com.yh.service.impl.EstatePersonServiceImple;

import net.sf.json.JSONObject;

public class AddEstatePerson extends HttpServlet{
	EstatePersonService geps= new EstatePersonServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		//System.out.println("123123");
		String uname=req.getParameter("uname1");
		String uphone=req.getParameter("uphone1");
		String uid=req.getParameter("uid1");
		//String ustarttime=req.getParameter("ustarttime");
		//String uendtime=req.getParameter("uendtime");
		//String currentTime=req.getParameter("currentTime");
		//获取当前时间
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        java.util.Date now = new java.util.Date();
        String resultDate = sdf.format(now);
        java.util.Date last = new java.util.Date(resultDate);
        //psmt.setDate(i, new java.sql.Date(last)) ;*/
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String currentTime=df.format(new Date());
		
		//数据库插入Date time= new java.sql.Date(newjava.util.Date().getTime());
//		Date time= new java.sql.Date(new java.util.Date().getTime());
//		System.out.println(time);
		
		System.out.println("页面："+uname+"-"+uphone+"-"+uid+"-"+currentTime);
		//将参数一模型方式传输
		Estate e =new Estate();
		e.setEname(uname);
		e.setEphone(uphone);
		e.setEid(uid);
		e.setCurrentTime(currentTime);
		//插入数据库
		boolean flag=geps.addEstatePersonByObj(e);
		//拼接写回，一定的格式不能变
		JSONObject j=new JSONObject();
		PrintWriter pw = resp.getWriter();
		if(flag){
			j.put("success", true);
			j.put("msg","添加成功");
			pw.write(j.toString());
		}else{
			j.put("success", false);
			j.put("msg","添加失败");
			pw.write(j.toString());
		}
	}
}
