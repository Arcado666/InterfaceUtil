package com.qiang.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断map里面所有key的value是否为null，并返回不是null的新map
 * @author Administrator
 *
 */
public class MapOfValueIsNotNull {
	public	Map<String,Object> getNewMap(Map<String, Object> oldMap){
		Map<String, Object> newMap = new HashMap<String,Object>();
		for(Map.Entry<String, Object> param : oldMap.entrySet()){
            String key = param.getKey();
            Object value = param.getValue();
            if (value != null) {
				newMap.put(key, value);
			}
            }
		
		return newMap;
	}
}
