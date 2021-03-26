package com.deloitte.SpaceStation.user.account;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountValidator {

    private final AccountRepository accountRepository;

    public void verifyUsernameUniqueness(String username) {
        if (accountRepository.existsByUsername(username)) {
            throw new SpaceStationException(Error.USERNAME_ALREADY_EXISTS);
        }
    }
}
