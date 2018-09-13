package com.example.monitor.dto.events;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Event {
	public String eventName = "Event";
	public Map<String, Object> headerMap = new HashMap();
	
	public Event(){};
	
	public Event(String name) {
		eventName = name;
	}
	
	public void addHeader(String key, Object value) {
		headerMap.put(key, value);
	}

}
