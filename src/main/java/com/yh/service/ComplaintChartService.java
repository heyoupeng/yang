package com.yh.service;

import net.sf.json.JSONObject;

/**
 * 投诉报表服务
 * @author ccy
 *
 */
public interface ComplaintChartService {
  public JSONObject getComplaintChart(String year);
}
