package com.deloitte.SpaceStation.security.login;

import com.deloitte.SpaceStation.AbstractTest;
import com.ocadotechnology.gembus.test.Arranger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldReturnStatusOk() throws Exception {
        String uri = "/login";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(Arranger.someText());
        loginRequest.setPassword(Arranger.someText());

        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void shouldReturnStatus400IfRequestBodyIsMissing() throws Exception {
        String uri = "/login";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content((byte[]) null)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

}