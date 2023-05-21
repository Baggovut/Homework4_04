package ru.skypro.lessons.springboot.weblibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Integer salary;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;

}
