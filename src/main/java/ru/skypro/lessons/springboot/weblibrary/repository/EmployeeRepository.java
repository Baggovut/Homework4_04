package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.skypro.lessons.springboot.weblibrary.model.Employee;
import ru.skypro.lessons.springboot.weblibrary.model.projections.EmployeeFullInfo;

import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.model.projections." +
            "EmployeeFullInfo(e.name , e.salary , p.positionName) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p AND e.id = :id")
    List<EmployeeFullInfo> findEmployeeByIdFullInfo(@Param("id") Integer id);
}
