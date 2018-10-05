package com.example.event.monitor.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import com.example.event.monitor.db.EventCounterService;
import com.example.util.dto.events.WMSEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

	// public static List<String> messageList = new ArrayList<String>();

	@Autowired
	EventCounterService eventCounterService;

	public RedisMessageSubscriber(EventCounterService eventCounterService) {
		this.eventCounterService = eventCounterService;
	}

	public void onMessage(Message message, byte[] pattern) {
		// messageList.add(message.toString());
		WMSEvent wmsEvent;
		GenericJackson2JsonRedisSerializer deSerializer = new GenericJackson2JsonRedisSerializer();
		wmsEvent = (WMSEvent) deSerializer.deserialize(message.getBody());
		log.info("Event received: " + wmsEvent);
		if (eventCounterService == null) {
			log.error("Event Counter Service is not initialized");
		} else {
			//eventStatisticsRepository.addNewEvent(wmsEvent);
			eventCounterService.addNewEvent(wmsEvent.getEventName());
			log.info("Get EventCounters for current hour: " + eventCounterService.getCurrentHourEventCounters());
		}
	}

	/* De serialize the byte array to object */
	private static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
		ObjectInput in = new ObjectInputStream(bis);
		return in.readObject();
	}
}