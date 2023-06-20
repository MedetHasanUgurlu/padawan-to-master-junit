package com.medet.medron.business;

import com.medet.medron.business.dto.EmployeeDto;
import java.util.List;
public interface EmployeeService {
    void add(EmployeeDto dto);
    void delete(int id);
    EmployeeDto get(int id);
    List<EmployeeDto> getAll();
}
