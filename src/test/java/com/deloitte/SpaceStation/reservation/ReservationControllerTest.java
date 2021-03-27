package com.deloitte.SpaceStation.reservation;

import com.deloitte.SpaceStation.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest extends AbstractTest {

    @Autowired
    private ReservationController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldReturnStatusCodeOK() throws Exception {
        // given
        String uri = "/api/reservations";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        // when
        // then
        assertEquals(200, status);
    }

    @Test
    public void shouldAssertThatControllerIsNotNull() {
        // then
        assertThat(controller).isNotNull();
    }
}