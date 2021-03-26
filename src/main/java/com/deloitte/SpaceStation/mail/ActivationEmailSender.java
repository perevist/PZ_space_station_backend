package com.deloitte.SpaceStation.mail;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.StringBuilderFormattable;
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
        mailService.sendMail(subject, email, content, true);
    }

    private String createActivationEmailContent(String verificationToken) {
        String link = new StringBuilder()
                .append(applicationUrl)
                .append("/registration/accountVerification/")
                .append(verificationToken)
                .toString();

        return new StringBuilder()
                .append("Hello,<br><br>")
                .append("Welcome to the Space Station. Click on the button below to activate your account:<br><br>")
                .append("<center><a href=\"")
                .append(link)
                .append("\" target=\"_blank\"><button> Activate your account! </button></a> </center><br>")
                .append("If the button does not work you can click ")
                .append("<a href=\"")
                .append(link)
                .append("\">here</a>.<br><br>")
                .append("Best regards, <br>Space Station Team")
                .toString();
    }
}
