package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.mail.ActivationEmailSender;
import com.deloitte.SpaceStation.registration.verificationtoken.VerificationTokenService;
import com.deloitte.SpaceStation.user.model.User;
import com.deloitte.SpaceStation.user.model.UserResponseDto;
import com.deloitte.SpaceStation.user.repository.UserRepository;
import com.deloitte.SpaceStation.user.util.UserResponseDtoMapper;
import com.deloitte.SpaceStation.user.util.UserValidator;
import com.deloitte.SpaceStation.util.FeedbackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final RegistrationRequestMapper registrationRequestMapper;
    private final UserResponseDtoMapper userResponseDtoMapper;
    private final VerificationTokenService verificationTokenService;
    private final ActivationEmailSender activationEmailSender;

    @Override
    @Transactional
    public UserResponseDto registerUser(RegistrationRequest registrationRequest) {
        userValidator.verifyUsernameUniqueness(registrationRequest.getUsername());
        userValidator.verifyEmailUniqueness(registrationRequest.getEmail());
        User user = registrationRequestMapper.mapRegistrationRequestToUser(registrationRequest);
        userRepository.save(user);
        sendActivationEmail(user);
        return userResponseDtoMapper.mapUserToUserResponseDto(user);
    }

    @Override
    @Transactional
    public FeedbackMessage activateAccount(String token) {
        var verificationToken = verificationTokenService.getVerificationToken(token);
        User user = verificationToken.getUser();
        user.getAccount().setEnabled(true);
        userRepository.save(user);
        return new FeedbackMessage("Account verification successfully");
    }

    private void sendActivationEmail(User user) {
        String token = verificationTokenService.generateVerificationToken(user);
        activationEmailSender.sendActivationEmail(user.getEmail(), token);
    }
}
