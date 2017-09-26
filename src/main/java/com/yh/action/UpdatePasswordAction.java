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

public class UpdatePasswordAction extends HttpServlet{
	AccountService as=new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	/**
	 * 状态码说明：200（修改成功）,400（未知错误导致修改失败）
	 * 401（已退出），402（旧密码不正确）,403（新密码为空）
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		HttpSession session=req.getSession();
		JSONObject obj=new JSONObject();
		Account user=(Account) session.getAttribute("user");
		String oldPassword=req.getParameter("oldPassword");
		String newPassword=req.getParameter("newPassword");
		//已退出（session已过期）
		if(user==null){
			obj.put("state", "401");
		}else{
			//判断旧密码是否错误
			if(user.getPassword().equals(oldPassword)){//旧密码正确
				if(newPassword==null){
					obj.put("state", "403");//新密码为空
				}else{
					if(as.updatePassword(user, newPassword)){//修改成功
						user.setPassword(newPassword);
						obj.put("state", "200");
					}else{//修改失败
						obj.put("state", "400");
					}
				}
			}else{//旧密码错误
				obj.put("state", "402");
			}
		}
		PrintWriter pw=resp.getWriter();
		pw.write(obj.toString());
	}
}
