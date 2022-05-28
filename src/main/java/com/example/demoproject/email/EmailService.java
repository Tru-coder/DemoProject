package com.example.demoproject.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendTextEmail(String destination, String theme, String message);

    void sendEmailWithAttachment();

    void sendHTMLEmail();
}
