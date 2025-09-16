package ems.ems._backend.service.impl;

import ems.ems._backend.dto.EmployeeDto;
import ems.ems._backend.Entity.Employee;
import ems.ems._backend.exception.ResourceNotFoundException;
import ems.ems._backend.mapper.EmployeeMapper;
import ems.ems._backend.repository.EmployeeRepository;
import ems.ems._backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saved = repository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saved);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updated = repository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        repository.delete(employee);
    }
}
