package jado.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class JsonSampleControllerTest {
	
	@InjectMocks
	private JsonSampleController jsonSampleController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(jsonSampleController).build();
	}
	
	@Test
	public void test() throws Exception {
		this.mockMvc.perform(get("/json").param("name", "name"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.no").value(1));
	}
	
	@Test
	public void test2() throws Exception {
		this.mockMvc.perform(get("/json2").param("name", "name"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].no").value(1))
			.andExpect(jsonPath("$.data[0].name").value("dummy"));
	}
}
