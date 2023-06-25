package com.medet.medron.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medet.medron.business.EmployeeService;
import com.medet.medron.business.dto.EmployeeDto;
import com.medet.medron.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest

class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeService service;
    @Test
    void add() throws Exception {
        Employee employee = new Employee(1,"B","C",1,"D");
        given(service.add(ArgumentMatchers.any(EmployeeDto.class))).willReturn(employee);

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.
                        post("/employee").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(employee)));
        response.andDo(MockMvcResultHandlers.print()).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(employee.getName())));
}

    @Test
    void getAll() {
    }
}