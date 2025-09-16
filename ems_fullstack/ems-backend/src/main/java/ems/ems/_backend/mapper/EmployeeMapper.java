package ems.ems._backend.mapper;

import ems.ems._backend.Entity.Employee;
import ems.ems._backend.dto.EmployeeDto;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        if (employee == null) return null;
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto dto) {
        if (dto == null) return null;
        return new Employee(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail()
        );
    }
}
