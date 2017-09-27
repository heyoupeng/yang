package com.yh.service;

import net.sf.json.JSONObject;

/**
 * 生成维修任务报表
 * @author ccy
 *
 */
public interface RepairChartService {
   public JSONObject getRepairChart(String year);
}
