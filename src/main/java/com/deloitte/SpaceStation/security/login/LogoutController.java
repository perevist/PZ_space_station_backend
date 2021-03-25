package com.deloitte.SpaceStation.security.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    // Endpoint for Swagger
    @PostMapping("/logout")
    public void logout() {
    }
}
