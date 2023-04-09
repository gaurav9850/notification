package com.notification.notify.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class Mail {

    private Logger logger = LoggerFactory.getLogger(Mail.class);
    @Value("${gmail.host}")
    private String host;

    @Value("${gmail.port}")
    private int port;

    @Value("${gmail.user}")
    private String username;

    @Value("${gmail.password}")
    private String password;

    @Value("${gmail.tls}")
    private String ssl;

    @Value("${gmail.auth}")
    private String auth;

    boolean flag = false;

    public Map<String, Object> sendMail(String to,
                                        String subject,
                                        String body) {

        logger.info("host : {}" , host);
        logger.info("port : {}" , port);
        logger.info("username : {}" , username);
        logger.info("password : {}" , password);
        logger.info("ssl : {}" , ssl);
        logger.info("auth : {}" , auth);
        logger.info("to : {}" , to);
        logger.info("subject : {}" , subject);
        logger.info("body : {}" , body);

        Map<String, Object> msg = new HashMap<>();
        try {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost(host);
            sender.setPort(port);
            sender.setUsername(username);
            sender.setPassword(password);

            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", auth);
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            properties.put("mail.smtp.starttls.enable", ssl);
            properties.put("mail.smtp.ssl.trust", host);
            sender.setJavaMailProperties(properties);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            sender.send(message);
            flag = true;

        } catch (Exception e) {
            msg.put("message", e.getMessage());
            msg.put("status", HttpStatus.NOT_FOUND);
        }
        if (flag) {
            msg.put("message", "message sent successfully");
            msg.put("status", HttpStatus.OK);
        } else {
            msg.put("message", "message sending failed");
            msg.put("status", HttpStatus.OK);
        }
        return msg;
    }
}