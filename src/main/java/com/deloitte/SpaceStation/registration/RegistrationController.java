package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public UserResponseDto registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return registrationService.registerUser(registrationRequest);
    }
}
