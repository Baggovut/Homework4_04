package ru.skypro.lessons.springboot.weblibrary.dto;

import lombok.Data;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.model.Position;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private Integer salary;
    //private String positionName;
    private Position position;
    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setPosition(employee.getPosition());
        //employeeDTO.setPositionName(employee.getPosition().getPositionName());
        return employeeDTO;
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        employee.setPosition(this.getPosition());
        //employee.getPosition().setPositionName(this.getPositionName());
        return employee;
    }
}
