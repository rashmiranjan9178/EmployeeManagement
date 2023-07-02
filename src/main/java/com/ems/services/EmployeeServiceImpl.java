package com.ems.services;

import com.ems.Payload.EmployeeDTO;
import com.ems.entities.Employee;
import com.ems.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(this::convertToDTO);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setCity(employeeDTO.getCity());
            existingEmployee.setEmail(employeeDTO.getEmail());
            existingEmployee.setMobile(employeeDTO.getMobile());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return convertToDTO(updatedEmployee);
        }
        return null; // Or throw an exception if the employee is not found
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEid(employee.getEid());
        employeeDTO.setName(employee.getName());
        employeeDTO.setCity(employee.getCity());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setMobile(employee.getMobile());
        return employeeDTO;
    }


    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEid(employeeDTO.getEid());
        employee.setName(employeeDTO.getName());
        employee.setCity(employeeDTO.getCity());
        employee.setEmail(employeeDTO.getEmail());
        employee.setMobile(employeeDTO.getMobile());
        return employee;
    }

}

