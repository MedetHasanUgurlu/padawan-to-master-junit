package com.medet.medron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medet.medron.business.dto.EmployeeDto;
import com.medet.medron.repository.EmployeeRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MedronApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void createTest() throws Exception {
		EmployeeDto employeeDto = new EmployeeDto(0,"A","B",34,"C");

		ResultActions actions = mockMvc.perform(
				MockMvcRequestBuilders.
						post("/employee").
						contentType(MediaType.APPLICATION_JSON).
						content(objectMapper.writeValueAsString(employeeDto))
		);

		actions.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is("A")));
	}


	@Test
	void getAll() throws Exception{
		ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/employee").contentType(MediaType.APPLICATION_JSON));

		actions.andDo(MockMvcResultHandlers.print()).
				andExpect(MockMvcResultMatchers.status().isOk());

	}

}
