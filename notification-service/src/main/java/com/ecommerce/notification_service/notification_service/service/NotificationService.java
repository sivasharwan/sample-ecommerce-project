package com.ecommerce.notification_service.notification_service.service;

import com.ecommerce.notification_service.notification_service.OrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void listenOrderPlaced(OrderPlacedEvent orderPlacedEvent) {
        log.info("Got a message from order-placed topic: {} with user email : {}", orderPlacedEvent.getOrderId(), orderPlacedEvent.getEmail());

        //Send email to the user
        log.info("Sending email to: " + orderPlacedEvent.getEmail() + "for order: " + orderPlacedEvent.getOrderId());
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("springshop@email.com");
            mimeMessageHelper.setTo(orderPlacedEvent.getEmail());
            mimeMessageHelper.setSubject("Order Placed");
            mimeMessageHelper.setText(String.format("""
                    Hi,
                                        
                    Your order has been placed successfully. Your order id is: %s.
                                        
                    Best Regards,
                    Spring Shop Team""", orderPlacedEvent.getOrderId()));
        };
        try {
            //Send email
            mailSender.send(mimeMessagePreparator);
            log.info("Order notification email sent successfully");
        } catch (Exception e) {
            log.error("Error sending email: " + e.getMessage());
        }

    }

}
