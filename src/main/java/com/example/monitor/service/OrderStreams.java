package com.example.monitor.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderStreams {
    public String ORDERS_INPUT = "orders-in";
    public String ORDERS_OUTPUT = "orders-out";
    
    @Input(ORDERS_INPUT)
    public SubscribableChannel inboundOrders();
    
    @Output(ORDERS_OUTPUT)
    public MessageChannel outboundOrders();
}