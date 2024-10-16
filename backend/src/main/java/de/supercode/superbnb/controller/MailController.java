package de.supercode.superbnb.controller;

import de.supercode.superbnb.services.MailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/newsletter")
public class MailController {

    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public void sendEmail() throws MessagingException {
        mailService.sendEmail("ultimate@warrior.sixpack","Netter Biceps", "Hallo Hogan, du bist mal cool gewesen.");
        mailService.sendHtmlEmail();
    }
}
