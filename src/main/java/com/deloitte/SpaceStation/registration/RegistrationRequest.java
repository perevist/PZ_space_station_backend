package com.deloitte.SpaceStation.registration;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class RegistrationRequest {
    @NotBlank(message = "First name can not be blank")
    private String firstName;
    @NotBlank(message = "Last name can not be blank")
    private String lastName;
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email must be in email format")
    private String email;
    @NotBlank(message = "Phone number can not be blank")
    @Pattern(regexp = "(^$|[0-9]{9})", message = "Phone number must have 9 digits")
    private String phoneNumber;
    @NotBlank(message = "Username can not be blank")
    @Size(min = 6, max = 30, message = "Username must be between 6 and 30 characters")
    private String username;
    @NotBlank(message = "Password can not be blank")
    @Size(min = 6, message = "Password length can not be less than 6")
    private String password;
}
