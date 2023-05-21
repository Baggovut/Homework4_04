package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.model.projections.EmployeeFullInfo;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    int getSalarySum();
    EmployeeDTO getEmployeeWithMinSalary();
    List<EmployeeDTO> getEmployeeWithMaxSalary();
    List<EmployeeDTO> getEmployeesWithHighSalary();
    void addEmployee(EmployeeDTO employeeDTO);
    void editEmployeeById(Integer id, EmployeeDTO employeeDTO);
    Employee getEmployeeById(Integer id);
    void deleteEmployeeById(Integer id);
    List<EmployeeDTO> getEmployeesWithSalaryHigherThan(Integer salary);
    List<EmployeeDTO> getEmployeesWithPosition(Integer positionId);
    List<EmployeeFullInfo> getEmployeeFullInfoById(Integer id);
    List<EmployeeDTO> getEmployeesByPage(Integer pageIndex);
}
