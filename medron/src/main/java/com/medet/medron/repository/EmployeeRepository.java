package com.medet.medron.repository;

import com.medet.medron.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByNameIgnoreCase(String name);
    Employee findByName(String name);

}
