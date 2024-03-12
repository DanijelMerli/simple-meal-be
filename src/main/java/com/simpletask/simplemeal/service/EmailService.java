package com.simpletask.simplemeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    @Scheduled(cron = "0 0 8 * * MON-FRI")
    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("simplemeal2024@gmail.com");
        message.setTo("zekanovicbojana4@gmail.com");
        message.setSubject("Today's Menu");
        message.setText(  "\n\n" + "Order now: ");
        javaMailSender.send(message);
    }
}
