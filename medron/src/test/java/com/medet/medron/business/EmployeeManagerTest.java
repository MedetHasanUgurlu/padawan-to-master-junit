package com.medet.medron.business;

import com.medet.medron.business.dto.EmployeeDto;
import com.medet.medron.entity.Employee;
import com.medet.medron.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeManagerTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeManager service;




    @BeforeEach
    void setUp() {
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        service = new EmployeeManager(employeeRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        EmployeeDto dto = new EmployeeDto(0,"Medet","Ugurlu",1000,"ABV");
        Employee e = Employee.builder().
                id(dto.id()).name(dto.name()).
                surName(dto.surName()).
                salary(dto.salary()).
                department(dto.department())
                .build();

        when(employeeRepository.save(any(Employee.class))).thenReturn(e);
        given(employeeRepository.existsByNameIgnoreCase(dto.name())).willReturn(false);
        Employee savedEmployee = service.add(dto);
        assertThat(savedEmployee).isNotNull();




    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
        Employee employee = new Employee(1,"Mehmet","Hun",5000,"CCC");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<EmployeeDto> employeeDtoList = service.getAll();
        System.out.println(employeeDtoList.get(0));

        assertThat(employeeDtoList.size()).isEqualTo(1);


    }
}