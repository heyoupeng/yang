package com.yh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.json.JsonArray;
import javax.persistence.criteria.CriteriaBuilder.In;

import com.yh.dao.OwnerWorkOrderDao;
import com.yh.dao.RepairDao;
import com.yh.dao.impl.OwnerWorkOrderDaoImpl;
import com.yh.dao.impl.RepairDaoImpl;
import com.yh.model.Repair;
import com.yh.service.RepairChartService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

public class RepairChartServiceImpl implements RepairChartService{
	OwnerWorkOrderDao odao = new OwnerWorkOrderDaoImpl();
	RepairDao rdao = new RepairDaoImpl();
	@Override
	public JSONObject getRepairChart(String year) {
		JSONObject data1 = new JSONObject();
		JSONArray labels = new JSONArray();
		labels.add("一月");labels.add("二月");labels.add("三月");labels.add("四月");labels.add("五月");labels.add("六月");
		labels.add("七月");labels.add("八月");labels.add("九月");labels.add("十月");labels.add("十一月");labels.add("十二月");
		JSONArray datasets = new JSONArray();
		List<Repair> R_nos = rdao.getAllR_noAndR_name();
		for (int i = 0; i < R_nos.size(); i++) {
		   HashMap<Integer,Integer>	 map = (HashMap<Integer, Integer>) odao.selectCountByTimeAndR_no(year,(int)R_nos.get(i).getNo());
		   if(!(map.isEmpty()))
		   {
			   JSONObject obj = new JSONObject();
			   obj.put("label", R_nos.get(i).getName());
			   obj.put("backgroundColor",getRandColorCode(i) );
			   String color = getRandColorCode(i);
			   obj.put("borderColor", color);
			   JSONArray data = new JSONArray();
			   for (int j = 1; j <=12; j++) {
				if(map.get(j) == null)
				{
					data.add(0);
				}
				else
				{
					data.add(map.get(j));
				}
			}
			   obj.put("data",data);
			   datasets.add(obj);
		   }
		}
		data1.put("labels", labels);
		data1.put("datasets", datasets);
		return data1;
	}
	
	public static String getRandColorCode(int i){  
		   String color1 = "#BBFFFF";
		   String color2 = "#00868B";
		   String color3 = "#96CDCD";
		   
		   String color4 = "#668B8B";
		   if(i%4==0)
		   {
			   return color1;
		   }
		   else if(i%4==1)
		   {
		  return color2;  
		   }
		   else if(i%4==2)
		   {
			   return color3; 
		   }
		   else
		   {
			   return color4;
		   }
		 }

}
