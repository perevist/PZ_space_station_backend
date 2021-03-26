package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.user.User;
import com.deloitte.SpaceStation.user.account.Account;
import com.deloitte.SpaceStation.user.account.authority.Authority;
import com.deloitte.SpaceStation.user.account.authority.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.deloitte.SpaceStation.user.account.authority.Authority.AuthorityName.ROLE_USER;

@Component
@RequiredArgsConstructor
public class RegistrationRequestMapper {

    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public User mapRegistrationRequestToUser(RegistrationRequest registrationRequest) {
        User user = getUserFromRegistrationRequest(registrationRequest);
        Account account = getAccountFromRegistrationRequest(registrationRequest);
        user.setAccount(account);
        return user;
    }

    private User getUserFromRegistrationRequest(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        return user;
    }

    private Account getAccountFromRegistrationRequest(RegistrationRequest registrationRequest) {
        Account account = new Account();
        account.setUsername(registrationRequest.getUsername());
        account.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setCredentialsNonExpired(true);
        account.setEnabled(true);
        Authority authority = authorityRepository.findByName(ROLE_USER);
        account.setAuthorities(Set.of(authority));
        return account;
    }
}
