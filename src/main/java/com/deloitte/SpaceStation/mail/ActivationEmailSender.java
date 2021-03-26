package com.deloitte.SpaceStation.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivationEmailSender {

    private final MailService mailService;

    @Value("${application.url}")
    private String applicationUrl;

    public void sendActivationEmail(String email, String verificationToken) {
        String subject = "Please activate your Account";
        String content = createActivationEmailContent(verificationToken);
        mailService.sendMail(subject, email, content);
    }

    private String createActivationEmailContent(String verificationToken) {
        return new StringBuilder()
                .append("Hello,\n\n")
                .append("Welcome to the Space Station. Click on the below url to activate your account:\n")
                .append(applicationUrl)
                .append("/registration/accountVerification/")
                .append(verificationToken)
                .toString();
    }
}
