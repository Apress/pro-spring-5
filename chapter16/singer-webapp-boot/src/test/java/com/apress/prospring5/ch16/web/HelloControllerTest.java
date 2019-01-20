package com.apress.prospring5.ch16.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by iuliana.cosmina on 7/16/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class HelloControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@Test
	public void homePage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(content().string(containsString("ProSpring5")));
	}

	@Test
	public void loginWithValidUserThenAuthenticated() throws Exception {
		SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
				.user("user")
				.password("user");

		mockMvc.perform(login)
				.andExpect(authenticated().withUsername("user"));
	}

}
