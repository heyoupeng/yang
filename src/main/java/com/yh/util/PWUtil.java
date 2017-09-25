package com.yh.util;

import java.io.PrintWriter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PWUtil {
	public static void write(PrintWriter pw ,JSONObject json){
		pw.write(json.toString());
		
	}
	public static void write(PrintWriter pw ,JSONArray json){
		pw.write(json.toString());
		
	}
	public static void colse(PrintWriter pw){
		pw.flush();
		pw.close();
	}
}
