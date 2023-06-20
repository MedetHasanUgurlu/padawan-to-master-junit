package com.medet.medron.api;

import com.medet.medron.business.EmployeeService;
import com.medet.medron.business.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;
    @PostMapping
    public void add(@RequestBody EmployeeDto dto){
        service.add(dto);
    }

    @GetMapping
    public List<EmployeeDto> getAll(){
       return service.getAll();
    }



}
