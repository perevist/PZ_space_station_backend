package com.deloitte.SpaceStation.user;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.user.account.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    private final AccountValidator accountValidator;

    public void verifyUsernameUniqueness(String username) {
        accountValidator.verifyUsernameUniqueness(username);
    }

    public void verifyEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new SpaceStationException(Error.EMAIL_ALREADY_EXISTS);
        }
    }
}
