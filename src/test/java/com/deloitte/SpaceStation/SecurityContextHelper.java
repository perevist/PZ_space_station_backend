package com.deloitte.SpaceStation;

import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class SecurityContextHelper {

    public static void createSecurityContext(String loggedUserId) {
        Authentication authentication = Mockito.mock(Authentication.class);
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.toString()).thenReturn(loggedUserId);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(principal);
        SecurityContextHolder.setContext(securityContext);
    }
}
