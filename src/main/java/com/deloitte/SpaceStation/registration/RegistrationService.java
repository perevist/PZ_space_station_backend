package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.user.UserResponseDto;
import com.deloitte.SpaceStation.util.FeedbackMessage;

public interface RegistrationService {
    UserResponseDto registerUser(RegistrationRequest registrationRequest);

    FeedbackMessage activateAccount(String token);
}
