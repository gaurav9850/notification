package com.notification.notify.controllers;

import com.notification.notify.entities.Notification;
import com.notification.notify.kafkaengine.Producer;
import com.notification.notify.services.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class NotificationController {

    @Autowired
    private NotifyService service;

    @GetMapping("/kafka")
    public Notification not(@RequestParam("msg") String msg) throws ExecutionException, InterruptedException {
        Notification notification = new Notification();
        notification.setMsg(msg);
        return service.notification(notification);
    }
}
