package me.annanjin.shop.service.impl;

import me.annanjin.shop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(String targetMail, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(targetMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendMessageWithAttachment(String targetMail, String subject, String text, String attachmentFilename, String attachmentPath) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
        messageHelper.setTo(targetMail);
        messageHelper.setSubject(subject);
        messageHelper.setText(text);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachmentPath));
        messageHelper.addAttachment(attachmentFilename, fileSystemResource);
        mailSender.send(mailMessage);
    }
}
