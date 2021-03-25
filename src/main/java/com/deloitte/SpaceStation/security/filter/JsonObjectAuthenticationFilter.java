package com.deloitte.SpaceStation.security.filter;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.security.login.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LoginRequest loginRequest = getLoginRequestFromHttpServletRequest(request);
        validateLoginRequest(loginRequest);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication;
        try {
            setDetails(request, token);
            authentication = this.getAuthenticationManager().authenticate(token);
        } catch (Exception e) {
            throw new SpaceStationException(Error.BAD_LOGIN_CREDENTIALS);
        }

        return authentication;
    }

    private LoginRequest getLoginRequestFromHttpServletRequest(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return objectMapper.readValue(stringBuilder.toString(), LoginRequest.class);
        } catch (IOException e) {
            throw new SpaceStationException(Error.BAD_REQUEST);
        }
    }

    private void validateLoginRequest(LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new SpaceStationException(Error.BAD_REQUEST);
        }
    }
}
