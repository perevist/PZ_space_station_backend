package com.deloitte.SpaceStation.security.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    // Endpoint for Swagger
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
    }
}
