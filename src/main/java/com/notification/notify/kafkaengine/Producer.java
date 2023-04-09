package com.notification.notify.kafkaengine;

import com.notification.notify.entities.Notification;
import com.notification.notify.util.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class Producer {
    @Autowired
    private Mail mail;

    @Autowired
    KafkaTemplate<String, Notification> kafkaTemplate;

    @Value("${topic}")
    private String topic;

    java.util.List<String> address = new ArrayList<>();

    public Notification sendMessage(Notification notification) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, Notification>> result = kafkaTemplate.send(topic, notification);
        address.add("gsoni0704@gmail.com");
        address.add("gauravsoni9850@gmail.com");
        address.add("sarlasoni389@gmail.com");
        address.add("gauravsoni5011@gmail.com");
        for (String ad : address) {
            mail.sendMail(ad, "testing", result.get().getProducerRecord().value().getMsg());
        }
        System.out.println(result.get().getProducerRecord().value());
        return result.get().getProducerRecord().value();
    }
}