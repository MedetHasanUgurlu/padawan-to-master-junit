package com.medet.medron.business;

import com.medet.medron.business.dto.EmployeeDto;
import com.medet.medron.entity.Employee;
import com.medet.medron.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeManager implements EmployeeService{
    private final EmployeeRepository repository;
    @Override
    public Employee add(EmployeeDto dto) {
        if(repository.existsByNameIgnoreCase(dto.name())){
            throw new RuntimeException("Name already exist");
        }
        return repository.save(customMapToEntity(dto));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public EmployeeDto get(int id) {
        return customMapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<EmployeeDto> getAll() {
        return repository.findAll().stream().map(this::customMapToDto).toList();
    }

    public Employee customMapToEntity(EmployeeDto dto){
        Employee e = new Employee();
        e.setId(dto.id());
        e.setName(dto.name());
        e.setSurName(dto.surName());
        e.setSalary(dto.salary());
        e.setDepartment(dto.department());
        return e;

    }
    public EmployeeDto customMapToDto(Employee e){
     EmployeeDto employeeDto = new EmployeeDto(e.getId(),e.getName(),e.getSurName(),e.getSalary(),e.getDepartment());
     return  employeeDto;
    }
}
