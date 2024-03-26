package com.simpletask.simplemeal.service;

import com.simpletask.simplemeal.model.Order;
import com.simpletask.simplemeal.model.OrderItem;
import com.simpletask.simplemeal.model.User;
import com.simpletask.simplemeal.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private DailyMenuService dailyMenuService;

    @Scheduled(cron = "0 0 8 ? * MON-FRI")
    public void sendEmail() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            sendDailyEmail(user.getEmail());
        }
    }

    @Scheduled(cron = "0 0 10 ? * MON-FRI")
    public void assignChosenOneAndSentEmail() {
        Date today = dailyMenuService.getToday();
        List<User> users = orderItemService.getUsersWhoOrderedToday(today);

        if (users.isEmpty()) return;
        User previousChosenOne = userRepository.findChosenOne();
        if (previousChosenOne != null) {
            roleService.revertToUserRole(previousChosenOne);
            userRepository.save(previousChosenOne);
        }
        Random random = new Random();
        User chosenOne = users.get(random.nextInt(users.size()));
        roleService.assignChosenOneRole(chosenOne);
        userRepository.save(chosenOne);

        sendChosenOneEmail(chosenOne, today);
    }

    public void sendChosenOneEmail(User chosenOne, Date today) {
        Optional<Order> orderForTodayOpt = orderService.findByDate(today);
        if (orderForTodayOpt.isEmpty()) return;
        Order orderForToday = orderForTodayOpt.get();

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("<html><body>");
        emailBody.append("Dear ").append(chosenOne.getFirstName()).append(",<br/><br/>");
        emailBody.append("You have been chosen as the one for today.<br/><br/>");
        emailBody.append("Order details for today:<br/>");
        emailBody.append("Order number: ").append(orderForToday.getId()).append("<br/>");
        emailBody.append("Items:<br/>");
        for (OrderItem item : orderForToday.getOrderItems()) {
            emailBody.append("- ").append(item.getMeal().getName()).append(": ").append(item.getMealCount()).append(" (").append(item.getRegularMealSize()).append(")<br/>");
        }
        emailBody.append("<br/>");
        emailBody.append("Total Price: $").append(orderForToday.getTotalPrice()).append("<br/><br/>");
        emailBody.append("This is the link for the checklist: <a href='http://localhost:4200/checklist'>Click here to view the checklist</a><br/><br/>");
        emailBody.append("Best regards,<br/>Your Topli obrok");
        emailBody.append("</body></html>");

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(chosenOne.getEmail());
            helper.setSubject("You are chosen one for today");
            helper.setText(emailBody.toString(), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendDailyEmail(String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Hello from Topli obrok!");
            String linkText = "Click here to view the daily menu";
            String linkUrl = "http://localhost:4200/order";
            helper.setText("<html><body>This is the link for the daily menu: <a href='" + linkUrl + "'>" + linkText + "</a></body></html>", true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
