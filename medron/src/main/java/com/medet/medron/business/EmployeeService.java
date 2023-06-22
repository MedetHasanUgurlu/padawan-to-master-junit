package com.medet.medron.business;

import com.medet.medron.business.dto.EmployeeDto;
import com.medet.medron.entity.Employee;

import java.util.List;
public interface EmployeeService {
    Employee add(EmployeeDto dto);
    void delete(int id);
    EmployeeDto get(int id);
    List<EmployeeDto> getAll();
}
