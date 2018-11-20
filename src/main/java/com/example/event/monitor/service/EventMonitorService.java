package com.example.event.monitor.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.event.monitor.db.AppEventRepository;
import com.threedsoft.packing.dto.responses.PackResourceDTO;
import com.threedsoft.picking.dto.responses.PickResourceDTO;
import com.threedsoft.util.dto.events.WMSEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventMonitorService {

	@Autowired
	AppEventRepository appEventRepository;

	@Transactional
	public void add(WMSEvent wmsEvent) {
		log.info("Received msg for event:" + wmsEvent.getEventName());
		String userId = "";
		if(wmsEvent.getEventName().equalsIgnoreCase("PickConfirmationEvent")) {
			PickResourceDTO pickConfirmDTO = (PickResourceDTO)wmsEvent.getEventResource();
			userId = pickConfirmDTO.getUserId();
		}
		else
		if(wmsEvent.getEventName().equalsIgnoreCase("PackConfirmationEvent")) {
			PackResourceDTO packConfirmDTO = (PackResourceDTO)wmsEvent.getEventResource();
			userId = packConfirmDTO.getUserId();
		}
		appEventRepository.insertAppEvent(wmsEvent.getBusName(), wmsEvent.getLocnNbr(), wmsEvent.getCompany(),
				wmsEvent.getDivision(), wmsEvent.getBusUnit(), wmsEvent.getEventName(), wmsEvent.getCreatedDttm(),
				wmsEvent.getCreatedDttm(), userId);
		log.info("processed msg for event:" + wmsEvent.getEventName());

	}

	public List<Map<String, Object>> getEventCounters(String busName, Integer locnNbr, Integer numOfDays) {
		return appEventRepository.getEventCountsHourlyFromDays(busName, locnNbr, numOfDays);
	}

}
