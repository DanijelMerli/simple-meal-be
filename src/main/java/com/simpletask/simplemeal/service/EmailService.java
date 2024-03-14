package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 8 ? * MON-FRI") 
    public void sendEmail() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            sendDailyEmail(user.getEmail());
        }
    }
    public void sendDailyEmail(String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Hello from Topli obrok!");
            String linkText = "Click here to view the weekly menu";
            String linkUrl = "http://localhost:4200/menu";
            helper.setText("<html><body>This is the link for the weekly menu: <a href='" + linkUrl + "'>" + linkText + "</a></body></html>", true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
