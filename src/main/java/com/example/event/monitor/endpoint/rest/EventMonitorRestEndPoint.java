package com.example.event.monitor.endpoint.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.event.monitor.service.EventMonitorService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/eventmonitor/v1")
@Api(value="Event Monitor Service", description="Operations pertaining to Monitoring of Events")
@RefreshScope
@Slf4j
public class EventMonitorRestEndPoint {
    @Autowired
    EventMonitorService eventMonitorService;
	
    @Value("${wms.service.health.msg: Event Monitor Service - Config Server is not working..please check}")
    private String healthMsg;
    
    @Value("${wms.service.ready.msg: Event Monitor Service - Not ready yet}")
    private String readyMsg;

	@GetMapping("/ready")
	public ResponseEntity ready() throws Exception {
		return ResponseEntity.ok(readyMsg);
	}
	
	@GetMapping("/health")
	public ResponseEntity health() throws Exception {
		return ResponseEntity.ok(healthMsg);
	}
	
	@GetMapping("/{busName}/{locnNbr}/eventcounters/{numOfDays}")
	public ResponseEntity getById(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr, @PathVariable("numOfDays") Integer numOfDays) throws IOException {
		try {
			return ResponseEntity.ok(eventMonitorService.getEventCounters(busName, locnNbr, numOfDays));
		} catch (Exception e) {
			log.error("Error Occured for busName:" + busName + ", locnNbr:" + locnNbr + " : " + e.getMessage());
			return ResponseEntity.badRequest().body(new ErrorRestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Occured for GET request busName:" + busName + ", locnNbr:" + locnNbr + " : " + e.getMessage()));
		}
	}
}
