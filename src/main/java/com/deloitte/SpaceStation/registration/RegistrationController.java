package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.user.UserResponseDto;
import com.deloitte.SpaceStation.util.FeedbackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public UserResponseDto registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return registrationService.registerUser(registrationRequest);
    }

    @GetMapping("/accountVerification/{token}")
    public FeedbackMessage verifyAccount(@PathVariable String token) {
        return registrationService.activateAccount(token);
    }
}
