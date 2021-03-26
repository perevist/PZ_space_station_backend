package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.user.UserResponseDto;

public interface RegistrationService {
    UserResponseDto registerUser(RegistrationRequest registrationRequest);
}
