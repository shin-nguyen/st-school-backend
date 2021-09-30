package com.stschools.service;

public interface MailService {
    void sendSimpleMessage(String to, String subject, String text);
}
