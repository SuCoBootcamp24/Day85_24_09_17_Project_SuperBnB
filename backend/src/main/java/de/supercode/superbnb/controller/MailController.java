package de.supercode.superbnb.controller;

import de.supercode.superbnb.services.MailService;
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
    public void sendEmail() {
        mailService.sendEmail("recipient@example.com", "Newsletter", "Welcome to our newsletter!");
        mailService.sendHtmlEmail();
    }
}
