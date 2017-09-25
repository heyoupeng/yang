package com.yh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.Property_infoService;
import com.yh.service.impl.Property_infoServiceImpl;

import net.sf.json.JSONObject;

public class InsertProperty_info extends HttpServlet{
	Property_infoService ps = new Property_infoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	  req.setCharacterEncoding("utf8");
    	  resp.setCharacterEncoding("utf8");
    	  String build = req.getParameter("build");
    	  String unit = req.getParameter("unit");
    	  String floor = req.getParameter("floor");
    	  String room = req.getParameter("room");
    	  String ownerId = req.getParameter("ownerId");
    	  String area = req.getParameter("area");
    	  String remark = req.getParameter("remark");
    	  int buildNo;int unitNo;int floorNo;int roomNo;double areaNo;int ownerIdNo;
    	  PrintWriter pw = resp.getWriter();
    	  if(isNumeric(build)&&isNumeric(unit)&&isNumeric(floor)&&isNumeric(room)&&isDouble(area))
    	  {
    		  buildNo = Integer.parseInt(build);
    		  unitNo = Integer.parseInt(unit);
    		  floorNo = Integer.parseInt(floor);
    		  roomNo = Integer.parseInt(room);
    		  areaNo = Double.parseDouble(area);
    		  if("".equals(ownerId)||ownerId==null)
		      {
    			  ownerIdNo=0;
		      }
		      else
		      {
		    	  ownerIdNo=Integer.parseInt(ownerId);
		      }
    		  int i = ps.insertProperty_info(buildNo, unitNo, floorNo, roomNo, areaNo, ownerIdNo, remark);
    		  JSONObject json = new JSONObject();
   		     json.put("state",i);
   		      pw.print(json.toString());
    	  }
    	  else
    	  {
    		   JSONObject json = new JSONObject();
    		   json.put("state",2);
    		   pw.print(json.toString());
    	  }
    	  pw.flush();
    	  pw.close();
    	  }
    
    
    public static boolean isNumeric(String str){   
        Pattern pattern = Pattern.compile("[0-9]*");   
        return pattern.matcher(str).matches();      
    }   
    public static boolean isDouble(String str)
    {
       try
       {
          Double.parseDouble(str);
          return true;
       }
       catch(NumberFormatException ex){}
       return false;
    }
}
