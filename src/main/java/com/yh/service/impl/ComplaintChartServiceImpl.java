package com.yh.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yh.dao.ComplaintDao;
import com.yh.dao.impl.ComplaintDaoImple;
import com.yh.service.ComplaintChartService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ComplaintChartServiceImpl implements ComplaintChartService{
	ComplaintDao cdao = new ComplaintDaoImple();
	@Override
	public JSONObject getComplaintChart(String year) {
		JSONObject data1 = new JSONObject();
		JSONArray labels = new JSONArray();
		labels.add("一月");labels.add("二月");labels.add("三月");labels.add("四月");labels.add("五月");labels.add("六月");
		labels.add("七月");labels.add("八月");labels.add("九月");labels.add("十月");labels.add("十一月");labels.add("十二月");
		JSONArray datasets = new JSONArray();
		   HashMap<Integer,Integer>	 map = (HashMap<Integer, Integer>) cdao.selectAllMonths(year);
		   if(!(map.isEmpty()))
		   {
			   JSONObject obj = new JSONObject();
			   obj.put("label", "投诉条数");
			   obj.put("backgroundColor","#00CCFF");
			   obj.put("borderColor", "#00CCFF");
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
		data1.put("labels", labels);
		data1.put("datasets", datasets);
		return data1;
	}
	
}
