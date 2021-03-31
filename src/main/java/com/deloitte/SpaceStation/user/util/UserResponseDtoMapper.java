package com.deloitte.SpaceStation.user.util;

import com.deloitte.SpaceStation.user.model.User;
import com.deloitte.SpaceStation.user.model.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoMapper {

    public UserResponseDto mapUserToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getAccount().getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
