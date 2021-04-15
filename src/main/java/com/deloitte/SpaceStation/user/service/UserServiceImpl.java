package com.deloitte.SpaceStation.user.service;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.user.model.User;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakRestTemplate template;

    @Value("${keycloak.auth-server-url}")
    private String endpoint;

    @Override
    public User getUserById(String id) {
        String endpointGetUser = endpoint + "/admin/realms/SpaceStation/users/" + id;
        try {
            ResponseEntity<User> response = template.getForEntity(endpointGetUser, User.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new SpaceStationException(Error.USER_NOT_FOUND);
        }
    }
}
