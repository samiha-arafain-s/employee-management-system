package ems.ems._backend.controller;

import ems.ems._backend.dto.EmployeeDto;
import ems.ems._backend.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return service.saveEmployee(employeeDto);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return service.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}
