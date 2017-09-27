package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Account;
import com.yh.service.QuitWork_infoService;
import com.yh.service.impl.QuitWork_infoServiceImpl;

import net.sf.json.JSONObject;

public class JudgePropertyResign extends HttpServlet{
	QuitWork_infoService qs = new QuitWork_infoServiceImpl();
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 doPost(req, resp);
}
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    req.setCharacterEncoding("utf8");
		    resp.setCharacterEncoding("utf8");
		    HttpSession session = req.getSession();
		    Account acc = (Account) session.getAttribute("user");
		    List<String> list = qs.judgePropertyResign(acc.getUsername());
		    PrintWriter pw = resp.getWriter();
		    if(list.isEmpty())
		    {
		    	JSONObject json = new JSONObject();
		    	json.put("state", 0);
		    	pw.write(json.toString());
		    }
		    else
		    {
		    	JSONObject json = new JSONObject();
		    	json.put("state", list);
		    	pw.write(json.toString());
		    }
		    pw.flush();
		    pw.close();
	} 
}
