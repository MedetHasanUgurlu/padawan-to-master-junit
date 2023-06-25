package com.medet.medron.api;

import com.medet.medron.business.EmployeeService;
import com.medet.medron.business.dto.EmployeeDto;
import com.medet.medron.entity.Employee;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Employee add(@RequestBody EmployeeDto dto){
        return service.add(dto);
    }

    @GetMapping
    public List<EmployeeDto> getAll(){
       return service.getAll();
    }



}
