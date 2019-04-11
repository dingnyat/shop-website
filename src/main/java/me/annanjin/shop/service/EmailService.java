package me.annanjin.shop.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String targetMail, String subject, String text);

    void sendMessageWithAttachment(String targetMail, String subject, String text, String attachmentFilename, String attachmentPath) throws MessagingException;
}
