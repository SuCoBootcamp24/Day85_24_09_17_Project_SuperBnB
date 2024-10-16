package de.supercode.superbnb.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  private JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("xXX@xxx.de");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHtmlEmail(){
        try {
            MimeMessage message = mailSender.createMimeMessage();

            message.setRecipients(MimeMessage.RecipientType.TO, "rene.behrens@aol.com");
            message.setSubject("mail-Title");
            message.setFrom("xXX@xxx.de");
            String htmlContent = """
                    <h1>This is an HTML email</h1>
                    <p>This is the body of the email.</p>
                    """;

            message.setContent(htmlContent, "text/html; charset=UTF-8");
            mailSender.send(message);
        } catch (MessagingException ex) {
            ex.getCause();
        }
    }
}
