package com.deloitte.SpaceStation.user.service;

import com.deloitte.SpaceStation.user.model.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByUsername(String username);

    UserResponseDto getUserByEmail(String email);

    List<UserResponseDto> getUsers();

    List<UserResponseDto> getUsersByFirstName(String firstName);

    List<UserResponseDto> getUsersByLastName(String lastName);

    List<UserResponseDto> getUsersByFirstNameAndLastName(String firstName, String lastName);

    UserResponseDto getCurrentLoggedUser();
}
