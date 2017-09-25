package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.yh.model.Account;
import com.yh.service.AccountService;
import com.yh.service.impl.AccountServiceImpl;
import net.sf.json.JSONObject;

public class Login extends HttpServlet {
	AccountService accountService = new AccountServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rid = req.getParameter("rid");
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		JSONObject obj = new JSONObject();
		Date islock = accountService.isLocked(name, rid);
		if (islock != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			obj.put("state", "402");
			obj.put("unLocktime", sdf.format(islock));
		} else if (accountService.volicateAccount(name, password, rid)) {
			obj.put("state", "200");// 验证正确
			Account acc = new Account(name, password, Integer.parseInt(rid));
			HttpSession session = req.getSession();
			session.setAttribute("user", acc);
			session.setAttribute(name+"errorTimes", 0);
			if (session.getAttribute("user") == null) {// 在其他地方有登录，被清除
				obj.put("state", "401");// 已有其他地方登录
			}
		} else {
			HttpSession session=req.getSession();
			if(session.getAttribute("errorName")==null){
				session.setAttribute("errorName", name);
				session.setAttribute(name+"errorTimes", 1);
			}else{
				//当前就这样
				String oldName=(String) session.getAttribute("errorName");
				if(oldName.equals(name)){
					int time=(int) session.getAttribute(name+"errorTimes")+1;
					session.setAttribute(name+"errorTimes", time);
					if(time==5){
						accountService.lock(name, rid);
					}
				}else{
					session.setAttribute("errorName", name);
					session.setAttribute(name+"errorTimes", 1);
				}
			}
			Date lockTime = accountService.isLocked(name, rid);
			if (lockTime != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				obj.put("state", "402");
				obj.put("unLocktime", sdf.format(lockTime));
			} else {
				obj.put("state", "400");// 帐号密码错误
			}
		}
		PrintWriter pw = resp.getWriter();
		pw.write(obj.toString());
	}
}
