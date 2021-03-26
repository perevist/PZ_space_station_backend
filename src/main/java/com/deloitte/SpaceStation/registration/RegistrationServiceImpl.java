package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.user.*;
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

    @Override
    @Transactional
    public UserResponseDto registerUser(RegistrationRequest registrationRequest) {
        userValidator.verifyUsernameUniqueness(registrationRequest.getUsername());
        userValidator.verifyEmailUniqueness(registrationRequest.getEmail());
        User user = registrationRequestMapper.mapRegistrationRequestToUser(registrationRequest);
        userRepository.save(user);
        return userResponseDtoMapper.mapUserToUserResponseDto(user);
    }
}
