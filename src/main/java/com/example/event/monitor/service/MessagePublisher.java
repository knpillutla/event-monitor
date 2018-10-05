package com.example.event.monitor.service;

import com.example.util.dto.events.WMSEvent;

public interface MessagePublisher {
    void publish(WMSEvent wmsEvent);
}
