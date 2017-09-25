package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.Owner_infoService;
import com.yh.service.impl.Owner_infoServiceImpl;

import net.sf.json.JSONObject;

/**
 * datagrid显示数据
 * @author ccy
 *
 */
public class ShowOwner_info extends HttpServlet{
	Owner_infoService os = new Owner_infoServiceImpl();
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
}
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      resp.setCharacterEncoding("utf8");
	      req.setCharacterEncoding("utf8");
	      String name = req.getParameter("name");
	      String id = req.getParameter("id");
	      String phone = req.getParameter("phone");
	      String state = req.getParameter("state");
	      if(name==null)
	      {
	    	  name="";
	      }
	      if(id==null)
	      {
	    	  id="";
	      }
	      if(phone==null)
	      {
	    	  phone="";
	      }
	      if(state==null)
	      {
	    	  state="";
	      }
	      String page = req.getParameter("page");//页码
	      String rows = req.getParameter("rows");//每页行数
	      JSONObject json = os.getLimitOwner_info(page,rows,name,id,phone,state);
	      PrintWriter pw = resp.getWriter();
	      pw.write(json.toString());
	      pw.flush();
	      pw.close();
	}
}
