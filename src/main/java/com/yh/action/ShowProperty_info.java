package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.Owner_infoService;
import com.yh.service.Property_infoService;
import com.yh.service.impl.Owner_infoServiceImpl;
import com.yh.service.impl.Property_infoServiceImpl;

import net.sf.json.JSONObject;

/**
 * datagrid，获取全部的房产信息用于表格显示
 * @author ccy
 *
 */
public class ShowProperty_info extends HttpServlet{
	Property_infoService ps = new Property_infoServiceImpl();
	  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	  @Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		      resp.setCharacterEncoding("utf8");
		      req.setCharacterEncoding("utf8");
		      String build = req.getParameter("build");
		      String unit = req.getParameter("unit");
		      String floor = req.getParameter("floor");
		      String room = req.getParameter("room");
		      int buildNo ;int unitNo ;int floorNo ;int roomNo;
		      if("".equals(build)||build==null)
		      {
		    	  buildNo=0;
		      }
		      else
		      {
		    	  buildNo=Integer.parseInt(build);
		      }
		      if("".equals(unit)||unit==null)
		      {
		    	  unitNo=0;
		      }
		      else
		      {
		    	  unitNo=Integer.parseInt(unit);
		      }
		      if("".equals(floor)||floor==null)
		      {
		    	  floorNo=0;
		      }
		      else
		      {
		    	  floorNo=Integer.parseInt(floor);
		      }
		      if("".equals(room)||room==null)
		      {
		    	  roomNo=0;
		      }
		      else
		      {
		    	  roomNo=Integer.parseInt(room);
		      }
		      String page = req.getParameter("page");//页码
		      String rows = req.getParameter("rows");//每页行数
		      JSONObject json = ps.getLimitOwner_info(page, rows,buildNo,unitNo,floorNo,roomNo);
		      PrintWriter pw = resp.getWriter();
		      pw.write(json.toString());
		      pw.flush();
		      pw.close();
		}
	}

