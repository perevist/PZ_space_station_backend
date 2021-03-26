package com.deloitte.SpaceStation.user;

import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoMapper {

    public UserResponseDto mapUserToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getAccount().getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getFirstName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
