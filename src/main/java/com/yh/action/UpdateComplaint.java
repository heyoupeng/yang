package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.model.Complaint;
import com.yh.service.ComplaintService;
import com.yh.service.impl.ComplaintServiceImple;

import net.sf.json.JSONObject;

public class UpdateComplaint extends HttpServlet {
	ComplaintService cs=new ComplaintServiceImple();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		
		String cid =req.getParameter("cid2");
		String mno = req.getParameter("mno2");
		String cremark = req.getParameter("cremark2");
		String cendtime = req.getParameter("cendtime2");

		// 获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		// System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String currentTime = df.format(new Date());
		// System.out.println(currentTime);
		// System.out.println(cendtime.concat(currentTime.substring(10)));
		String DeadLine = cendtime.concat(currentTime.substring(10));
		
		Complaint c=new Complaint();
		c.setCid(Integer.parseInt(cid));
		c.setMno(Integer.parseInt(mno));
		c.setCremark(cremark);
		c.setCendtime(DeadLine);
		
		// 更新数据库
		boolean flag = cs.changeComplaintByObj(c);
		// 拼接写回，一定的格式不能变
		JSONObject j = new JSONObject();
		PrintWriter pw = resp.getWriter();
		if (flag==true) {
			j.put("success", true);
			j.put("msg", "修改成功");
			pw.write(j.toString());
		} else {
			j.put("success", false);
			j.put("msg", "修改失败");
			pw.write(j.toString());
		}
	}
}
