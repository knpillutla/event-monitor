package com.example.event.monitor.db;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppEventRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertAppEvent(String busName, Integer locnNbr, String company, String division, String busUnit,
			String eventName, LocalDateTime createdDttm, LocalDateTime updateDttm, String createdBy) {
		String insertsql = "insert into app_events (bus_name, locn_nbr, company, division, bus_unit, event_name,"
				+ "created_dttm, updated_dttm,created_by) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(insertsql, busName, locnNbr, company, division, busUnit, eventName, createdDttm, 
				updateDttm, createdBy);
		
	}

	public List<Map<String, Object>>  getEventCountsHourlyFromDays(String busName, Integer locnNbr, int numOfDays) {
		String timeBucket = "1 hour"; //
		String timeInterval = numOfDays<=0?("1 hour"):(numOfDays + " day");
		
		String selectSql = "SELECT time_bucket('" + timeBucket + "', created_dttm) AS time_bucket," +
	    "event_name, COUNT(*) FROM app_events " + 
	  "WHERE bus_name=? and locn_nbr=? and created_dttm > NOW() - interval '" + timeInterval + "' " +
	  "GROUP BY time_bucket, event_name " +
	  "ORDER BY time_bucket DESC";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(selectSql, busName, locnNbr);
		return resultList;
	}

	public List<Map<String, Object>>  getTopPickingPerformersHourlyFromDays(String busName, Integer locnNbr, 
			String eventName,int numOfDays) {
		String timeBucket = "1 hour"; //
		String timeInterval = numOfDays<=0?("1 hour"):(numOfDays + " day");
		
		String selectSql = "SELECT time_bucket('" + timeBucket + "', created_dttm) AS time_bucket," +
	    "created_by, COUNT(*) FROM app_events " + 
	  "WHERE bus_name=? and locn_nbr=? and event_name=? and created_dttm > NOW() - interval '" + timeInterval + "' " +
	  "GROUP BY time_bucket, created_by " +
	  "ORDER BY time_bucket DESC, count(*) desc";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(selectSql, busName, locnNbr, eventName);
		return resultList;
	}
}
