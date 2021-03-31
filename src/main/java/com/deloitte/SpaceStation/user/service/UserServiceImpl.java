package com.deloitte.SpaceStation.user.service;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.user.model.UserResponseDto;
import com.deloitte.SpaceStation.user.repository.UserRepository;
import com.deloitte.SpaceStation.user.util.UserResponseDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseDtoMapper userResponseDtoMapper;

    @Override
    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userResponseDtoMapper::mapUserToUserResponseDto)
                .orElseThrow(() -> new SpaceStationException(Error.USER_NOT_FOUND));
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userResponseDtoMapper::mapUserToUserResponseDto)
                .orElseThrow(() -> new SpaceStationException(Error.USER_NOT_FOUND));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userResponseDtoMapper::mapUserToUserResponseDto)
                .orElseThrow(() -> new SpaceStationException(Error.USER_NOT_FOUND));
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userResponseDtoMapper::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDto> getUsersByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName).stream()
                .map(userResponseDtoMapper::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDto> getUsersByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName).stream()
                .map(userResponseDtoMapper::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDto> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findAllByFirstNameAndLastName(firstName, lastName).stream()
                .map(userResponseDtoMapper::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }
}
