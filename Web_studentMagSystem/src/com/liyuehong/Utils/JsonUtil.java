package com.liyuehong.Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	 public static JSONArray resultToArray(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		JSONArray array = new JSONArray();
		while(rs.next()){
			JSONObject mapOfValue = new JSONObject();
			for(int i=1;i<=num;i++){
				Object o=rs.getObject(i);
				if(o instanceof Date){
					mapOfValue.put(md.getColumnName(i), DateUtil.formatDate((Date)o, "yyyy-MM-dd"));
				}else{
					mapOfValue.put(md.getColumnName(i), rs.getObject(i));					
				}
			}
			array.add(mapOfValue);
		}
		 return array; 
	 }
}
