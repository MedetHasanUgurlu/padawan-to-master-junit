package com.medet.medron.business;

import com.medet.medron.business.dto.EmployeeDto;
import com.medet.medron.entity.Employee;
import com.medet.medron.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@Log4j2
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
        Employee employee = new Employee(0,"Medet","Ugurlu",76,"EEE");
        given(employeeRepository.existsByNameIgnoreCase(any())).willReturn(false);
        given(employeeRepository.save(any(Employee.class))).willReturn(employee);
        Employee savedEmployee = service.add(new EmployeeDto(0,"a","b",34,"c"));
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void delete() {
        int id  = 1;
        willDoNothing().given(employeeRepository).deleteById(id);
        service.delete(id);
        verify(employeeRepository,times(1)).deleteById(id);

    }

    @Test
    void get() {
        Employee employee = new Employee(1,"Hasan","Ugurlu",34,"CCC");
        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        EmployeeDto employeeDto = service.get(employee.getId());
        log.info(employeeDto);

        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.name()).isEqualTo(employee.getName());


    }

    @Test
    void getAll() {
        List<Employee> employeeList = List.of(
                new Employee(0,"Hasan","Ugurlu",34,"CCC"),
                new Employee(0,"Medet","Ugurlu",76,"EEE"));
        given(employeeRepository.findAll()).willReturn(employeeList);
        System.out.println(employeeList);
        System.out.println(service.getAll());
        assertThat(service.getAll()).isNotNull();
    }
}