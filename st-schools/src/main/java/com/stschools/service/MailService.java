package com.stschools.service;

import org.springframework.messaging.MessagingException;

import java.util.Map;

public interface MailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageHtml(String to, String subject, String template, Map<String, Object> attributes) throws MessagingException;
}
