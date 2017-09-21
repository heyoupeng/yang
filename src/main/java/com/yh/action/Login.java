package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.model.Account;
import com.yh.service.AccountService;
import com.yh.service.impl.AccountServiceImpl;

import net.sf.json.JSONObject;

public class Login extends HttpServlet{
	AccountService accountService=new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rid=req.getParameter("rid");
		String name=req.getParameter("username");
		String password=req.getParameter("password");
		JSONObject obj=new JSONObject();
		if(accountService.volicateAccount(name, password, rid)){
			obj.put("state", "200");
			Account acc=new Account(name,password,Integer.parseInt(rid));
			HttpSession session=req.getSession();
			session.setAttribute("user", acc);
		}else{
			obj.put("state", "400");
		}
		PrintWriter pw=resp.getWriter();
		pw.write(obj.toString());
		
	}
}
