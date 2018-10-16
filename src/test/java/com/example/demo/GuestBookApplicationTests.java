package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.model.GuestBook;
import com.nt.model.Responce;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestBookApplicationTests {
	@Autowired
	private MockMvc mock;
	@Autowired
	private WebApplicationContext ctx;
	ObjectMapper mapper = new ObjectMapper();

	@PostConstruct
	public void setUp() {
		mock = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void addGuuestTest() throws Exception {
		GuestBook guest = new GuestBook();
		guest.setFirstName("basant");
		guest.setLastName("hota");
		guest.setEmail("hota@gmail.com");
		String jsonRequest = mapper.writeValueAsString(guest);
		MvcResult result = (MvcResult) mock
				.perform(post("/controller/add").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Responce responce = mapper.readValue(resultContent, Responce.class);
		Assert.assertTrue(responce.isStatus() == Boolean.TRUE);
	}

	@Test
	public void getGuuestTest() throws Exception {
		
		MvcResult result = (MvcResult) mock
				.perform(get("/controller/getAll").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Responce responce = mapper.readValue(resultContent, Responce.class);
		Assert.assertTrue(responce.isStatus() == Boolean.TRUE);
	}
	@Test
	public void updateGuestTest() throws Exception {
		int id=1;
		MvcResult result = (MvcResult) mock
				.perform(put("/controller/updateOne").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Responce responce = mapper.readValue(resultContent, Responce.class);
		Assert.assertTrue(responce.isStatus() == Boolean.TRUE);
	}
}
