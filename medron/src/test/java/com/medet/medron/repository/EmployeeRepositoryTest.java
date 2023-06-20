package com.medet.medron.repository;

import com.medet.medron.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

@SuppressWarnings({"java:S5838"})
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repository;
    private Employee e;
    @BeforeEach
    void setup(){
        e = Employee.builder().name("Metehan").surName("Ugurlu").department("TSK").salary(30000).build();
    }

    @Test
    void saveTest() {
        repository.save(e);

        Assertions.assertThat(e).isNotNull();
        Assertions.assertThat(e.getId()).isGreaterThan(0);
        assertThat(e.getName()).isNotEmpty();
    }

    @Test
    void getAll() {
        repository.save(e);
        List<Employee> employeeList = repository.findAll();
        assertThat(employeeList.size()).isGreaterThan(0);
    }
    @Test
    void delete(){
        repository.save(e);
        repository.deleteById(e.getId());
        assertThat(repository.findById(e.getId())).isEmpty();
    }
}