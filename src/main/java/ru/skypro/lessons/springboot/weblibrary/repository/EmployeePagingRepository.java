package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;

public interface EmployeePagingRepository extends PagingAndSortingRepository<Employee,Integer> {
}
