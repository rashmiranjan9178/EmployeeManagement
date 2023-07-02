package com.ems.services;

import com.ems.Payload.EmployeeDTO;
import com.ems.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    Optional<EmployeeDTO> getEmployeeById(Long id);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
}

