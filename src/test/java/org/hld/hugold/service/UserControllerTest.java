package org.hld.hugold.service;

import org.hld.hugold.controller.UserController;
import org.hld.hugold.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
 private UserController userController;
 
	@Autowired
	private MockMvc mvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
	}
	@Test
	public void addUser() throws Exception{
		
		int userID=5;
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new User(userID, "Gonzag",45)))).andDo(MockMvcResultHandlers.print()).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
	}
	
}
