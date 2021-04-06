package com.deloitte.SpaceStation.user.controller;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.user.model.UserResponseDto;
import com.deloitte.SpaceStation.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public UserResponseDto getUser(@Positive(message = "Id must be positive number")
                                   @RequestParam(required = false) Long id,
                                   @Size(min = 6, max = 30, message = "Username must be between 6 and 30 characters")
                                   @RequestParam(required = false) String username,
                                   @Email(message = "Email must be in email format")
                                   @RequestParam(required = false) String email) {

        if (id != null) {
            return userService.getUserById(id);
        } else if (username != null) {
            return userService.getUserByUsername(username);
        } else if (email != null) {
            return userService.getUserByEmail(email);
        } else {
            throw new SpaceStationException(Error.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public List<UserResponseDto> getUsersList(@RequestParam(required = false) String firstName,
                                              @RequestParam(required = false) String lastName) {

        if (firstName != null && lastName != null) {
            return userService.getUsersByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            return userService.getUsersByFirstName(firstName);
        } else if (lastName != null) {
            return userService.getUsersByLastName(lastName);
        } else {
            return userService.getUsers();
        }
    }

    @GetMapping("/currentLoggedUser")
    public UserResponseDto getLoggedInUserInfo() {
        return userService.getCurrentLoggedUser();
    }
}
