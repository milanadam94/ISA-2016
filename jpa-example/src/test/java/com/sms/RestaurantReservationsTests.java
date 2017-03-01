package com.sms;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sms.utilities.Message;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantReservationsTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testAddFriend() throws Exception {
		this.mvc.perform(post("/guest/addFriend/{guestId}", 2).contentType(MediaType.APPLICATION_JSON_VALUE).content("2")).andExpect(status().isOk())
				.andExpect(content().string(Message.REQUESTERROR)).andReturn();
		
		this.mvc.perform(post("/guest/addFriend/{guestId}", 1).contentType(MediaType.APPLICATION_JSON_VALUE).content("2")).andExpect(status().isOk())
		.andExpect(content().string(Message.ERRORFREE)).andReturn();
		
		this.mvc.perform(post("/guest/addFriend/{guestId}", 20).contentType(MediaType.APPLICATION_JSON_VALUE).content("2")).andExpect(status().isOk())
		.andExpect(content().string(Message.REQUESTERROR)).andReturn();
		
		this.mvc.perform(post("/guest/addFriend/{guestId}", 2).contentType(MediaType.APPLICATION_JSON_VALUE).content("50")).andExpect(status().isOk())
		.andExpect(content().string(Message.REQUESTERROR)).andReturn();
		
		this.mvc.perform(post("/guest/addFriend/{guestId}", 1).contentType(MediaType.APPLICATION_JSON_VALUE).content("3")).andExpect(status().isOk())
		.andExpect(content().string(Message.ERRORFREE)).andReturn();
		
	}
	
	@Test
	public void removeFriend() throws Exception {
		this.mvc.perform(post("/guest/removeFriend/{guestId}", 2).contentType(MediaType.APPLICATION_JSON_VALUE).content("2")).andExpect(status().isOk())
				.andExpect(content().string(Message.ERRORFREE)).andReturn();
		
		this.mvc.perform(post("/guest/removeFriend/{guestId}", 1).contentType(MediaType.APPLICATION_JSON_VALUE).content("2")).andExpect(status().isOk())
		.andExpect(content().string(Message.ERRORFREE)).andReturn();
		
		this.mvc.perform(post("/guest/removeFriend/{guestId}", 20).contentType(MediaType.APPLICATION_JSON_VALUE).content("2")).andExpect(status().isOk())
		.andExpect(content().string(Message.ERRORFREE)).andReturn();
		
		this.mvc.perform(post("/guest/removeFriend/{guestId}", 2).contentType(MediaType.APPLICATION_JSON_VALUE).content("50")).andExpect(status().isOk())
		.andExpect(content().string(Message.ERRORFREE)).andReturn();
		
		this.mvc.perform(post("/guest/removeFriend/{guestId}", 1).contentType(MediaType.APPLICATION_JSON_VALUE).content("3")).andExpect(status().isOk())
		.andExpect(content().string(Message.ERRORFREE)).andReturn();
		
	}
	
}