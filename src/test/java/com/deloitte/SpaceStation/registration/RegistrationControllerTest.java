package com.deloitte.SpaceStation.registration;

import com.deloitte.SpaceStation.AbstractTest;
import com.ocadotechnology.gembus.test.Arranger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class RegistrationControllerTest extends AbstractTest {
    @Autowired
    private RegistrationController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldAssertThatControllerIsNotNull() {
        // then
        assertThat(controller).isNotNull();
    }


    @Test
    public void shouldRegisterUserAndReturnStatusOK() throws Exception {
        // given
        String uri = "/registration";
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setFirstName(Arranger.someText());
        registrationRequest.setLastName(Arranger.someText());
        registrationRequest.setEmail(Arranger.someEmail());
        registrationRequest.setPassword(Arranger.someText());
        registrationRequest.setPhoneNumber("123456789");
        registrationRequest.setUsername(Arranger.someText());

        String inputJson = super.mapToJson(registrationRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        // when
        // then
        assertEquals(200, status);

    }

    @Test
    public void shouldReturnErrorIfPhoneNumberHaveNotNineDigits() throws Exception {
        // given
        String uri = "/registration";
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setFirstName(Arranger.someText());
        registrationRequest.setLastName(Arranger.someText());
        registrationRequest.setEmail(Arranger.someEmail());
        registrationRequest.setPassword(Arranger.someText());
        registrationRequest.setPhoneNumber("123");
        registrationRequest.setUsername(Arranger.someText());

        String inputJson = super.mapToJson(registrationRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String error = mvcResult.getResponse().getContentAsString();
        // when
        // then
        assertEquals(400, status);
        assertEquals("Phone number must have 9 digits", error.substring(12, error.length() - 3));
    }

    @Test
    public void shouldReturnErrorIfEmailIsIncorrect() throws Exception {
        // given
        String uri = "/registration";
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setFirstName(Arranger.someText());
        registrationRequest.setLastName(Arranger.someText());
        registrationRequest.setEmail(Arranger.someText());
        registrationRequest.setPassword(Arranger.someText());
        registrationRequest.setPhoneNumber("123456789");
        registrationRequest.setUsername(Arranger.someText());

        String inputJson = super.mapToJson(registrationRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String error = mvcResult.getResponse().getContentAsString();
        // when
        // then
        assertEquals(400, status);
        assertEquals("Email must be in email format", error.substring(12, error.length() - 3));
    }

    @Test
    public void shouldReturnErrorIfPasswordIsTooShort() throws Exception {
        // given
        String uri = "/registration";
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setFirstName(Arranger.someText());
        registrationRequest.setLastName(Arranger.someText());
        registrationRequest.setEmail(Arranger.someEmail());
        registrationRequest.setPassword(Arranger.someText(5,5));
        registrationRequest.setPhoneNumber("123456789");
        registrationRequest.setUsername(Arranger.someText());

        String inputJson = super.mapToJson(registrationRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String error = mvcResult.getResponse().getContentAsString();
        // when
        // then
        assertEquals(400, status);
        assertEquals("Password length must be between 6 and 30 characters", error.substring(12, error.length() - 3));
    }

    @Test
    public void shouldReturnErrorIfPasswordIsTooLong() throws Exception {
        // given
        String uri = "/registration";
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setFirstName(Arranger.someText());
        registrationRequest.setLastName(Arranger.someText());
        registrationRequest.setEmail(Arranger.someEmail());
        registrationRequest.setPassword(Arranger.someText(31, 31));
        registrationRequest.setPhoneNumber("123456789");
        registrationRequest.setUsername(Arranger.someText());

        String inputJson = super.mapToJson(registrationRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String error = mvcResult.getResponse().getContentAsString();
        // when
        // then
        assertEquals(400, status);
        assertEquals("Password length must be between 6 and 30 characters", error.substring(12, error.length() - 3));
    }

}