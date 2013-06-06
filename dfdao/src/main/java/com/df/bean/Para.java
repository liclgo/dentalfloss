package com.df.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//spring-mongoDb 不支持枚举 没办法了 用类吧
public class Para {

	public static List<String> list = new ArrayList<String>();
	static {
		list.add("text");
		list.add("select");
		list.add("time");
		list.add("checkbox");
		list.add("radio");
	}

	private String type;

	private String filedName;

	private Map<?, ?> paraMap;

	public String getType() {
		return type;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<?, ?> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<?, ?> paraMap) {
		this.paraMap = paraMap;
	}

	public static List<String> values() {
		return list;
	}

	public static Map<String, Queue<String>> analyse(Map<String, String[]> map) {
		Map<String, Queue<String>> map2 = new HashMap<String, Queue<String>>();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			for (String s : list) {
				if (key.startsWith(s)) {
					String[] values = map.get(key);
					Queue<String> queue = new LinkedList<>();
					List<String> list2 = new ArrayList<String>();
					for(String sb :values){
						if(sb.length()!=0){
							list2.add(sb);
						}
					}
					queue.addAll(list2);
					map2.put(key, queue);
				}
			}
		}
		return map2;
	}

}
