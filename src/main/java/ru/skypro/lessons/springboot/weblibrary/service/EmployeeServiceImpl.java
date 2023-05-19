package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.exceptions.IdNotFoundException;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.model.Position;
import ru.skypro.lessons.springboot.weblibrary.model.projections.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeePagingRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.PositionRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final EmployeePagingRepository employeePagingRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PositionRepository positionRepository,
                               EmployeePagingRepository employeePagingRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.employeePagingRepository = employeePagingRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return  employeeList.stream()
                .map(EmployeeDTO::fromEmployee).collect(Collectors.toList());
    }

    @Override
    public int getSalarySum() {
        return getAllEmployees()
                .stream()
                .mapToInt(EmployeeDTO::getSalary)
                .sum();
    }

    @Override
    public EmployeeDTO getEmployeeWithMinSalary() {
        return getAllEmployees()
                .stream()
                .min(Comparator.comparing(EmployeeDTO::getSalary))
                .orElseThrow();
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithMaxSalary() {
        EmployeeDTO singleEmployeeWithMaxSalary = getAllEmployees()
                .stream()
                .max(Comparator.comparing(EmployeeDTO::getSalary))
                .orElseThrow();
        return getAllEmployees()
                .stream()
                .filter(e -> Objects.equals(e.getSalary(), singleEmployeeWithMaxSalary.getSalary()))
                .toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithHighSalary() {
        int averageSalary = getSalarySum()/getAllEmployees().size();
        return getAllEmployees()
                .stream()
                .filter(e -> e.getSalary() >= averageSalary)
                .toList();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void editEmployeeById(Integer id, EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeById(id);
        if(employee != null){
            employeeDTO.setId(id);
            employeeRepository.save(employeeDTO.toEmployee());
        }
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Сотрудник с id="+id+" не найден"));
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        Employee employee1 = getEmployeeById(id);
        if(employee1 != null){
            employeeRepository.deleteById(id);
        }
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithSalaryHigherThan(Integer salary) {
        return getAllEmployees()
                .stream()
                .filter(e -> e.getSalary() > salary)
                .toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithPosition(Integer positionId) {
        if(positionId == null){
            return getAllEmployees();
        }
        Position foundedPosition = positionRepository
                .findById(positionId)
                .orElseThrow(
                        () -> new IdNotFoundException("Должность с position_id="+positionId+" не найдена")
                );

        return foundedPosition.getEmployeeList()
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeFullInfo> getEmployeeFullInfoById(Integer id) {
        return employeeRepository.findEmployeeByIdFullInfo(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByPage(Integer pageIndex) {
        int unitsPerPage = 10;
        if (pageIndex == null){
            pageIndex = 0;
        }

        Pageable employeeOfConcretePage = PageRequest.of(pageIndex, unitsPerPage);
        Page<Employee> page = employeePagingRepository.findAll(employeeOfConcretePage);

        return page
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }
}
