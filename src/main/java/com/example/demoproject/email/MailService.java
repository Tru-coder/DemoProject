package com.example.demoproject.email;

import com.example.demoproject.Models.User;
import com.example.demoproject.Pojo.UserPojo.UserCreatingPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MailService {

    private final EmailServiceImpl mailSenderService;

    public void successfulRegistration(UserCreatingPojo user){
        String message = String.format(
                """
                        Hello, %s!\s
                        Welcome to DemoProject
                        You password is %s""",
                    user.getUsername(),
                    user.getPassword());

        mailSenderService.sendTextEmail(user.getEmail(), "Successful Registration" , message);
    }
}

