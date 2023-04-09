package com.notification.notify.services;

import com.notification.notify.entities.Notification;
import com.notification.notify.kafkaengine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class NotifyService {

    @Autowired
    private Producer producer;

    public Notification notification(Notification notification) throws ExecutionException, InterruptedException {
        return producer.sendMessage(notification);
    }
}