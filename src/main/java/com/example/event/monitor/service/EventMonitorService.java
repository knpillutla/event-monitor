package com.example.event.monitor.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.threedsoft.util.dto.events.WMSEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventMonitorService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*
	 * @Autowired EventCounterService eventCounterService;
	 */
	@Transactional
	public void add(WMSEvent wmsEvent) {
		/*
		 * log.info("Received msg and publishing to redis channel:" +
		 * wmsEvent.getEventName()); redisMessagePublisher.publish(wmsEvent);
		 * log.info("published to redis channel:" + wmsEvent.getEventName());
		 */
		log.info("Received msg for event:" + wmsEvent.getEventName());
/*		eventRepository.addEvent(wmsEvent.getBusName(), wmsEvent.getLocnNbr(), wmsEvent.getCompany(), wmsEvent.getDivision(),
				wmsEvent.getBusUnit(), wmsEvent.getEventName(), wmsEvent.getCreatedDttm());
*/		
		
		String insertsql = "insert into app_events (bus_name, locn_nbr, company, division, bus_unit, event_name,"
				+ "created_dttm, updated_dttm) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(insertsql, wmsEvent.getBusName(), wmsEvent.getLocnNbr(), wmsEvent.getCompany(),wmsEvent.getDivision(),wmsEvent.getBusUnit(),wmsEvent.getEventName(),wmsEvent.getCreatedDttm(),wmsEvent.getCreatedDttm());
		log.info("processed msg for event:" + wmsEvent.getEventName());

	}
	/*
	 * public Map<String,Map<String, Long>> getEventCounters(String busName, Integer
	 * locnNbr, Integer numOfDays) { return
	 * eventCounterService.getEventCounters(numOfDays); }
	 */
}
