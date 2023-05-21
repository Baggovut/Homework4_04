package ru.skypro.lessons.springboot.weblibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Integer positionId;
    @Column(name = "position_name")
    private String positionName;

    @OneToMany(mappedBy = "position")
    //@JoinColumn(name="position_id")
    @JsonBackReference
    @JsonIgnoreProperties("employeeList")
    @JsonIgnore
    List<Employee> employeeList;
}
