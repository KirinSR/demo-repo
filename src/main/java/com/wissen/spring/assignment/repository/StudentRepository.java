package com.wissen.spring.assignment.repository;

import com.wissen.spring.assignment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStandard(String standard);
    List<Student> findBySchool(String school);
    List<Student> findByPercentageGreaterThan(Double percentage);
    List<Student> findTop3ByOrderByPercentageDesc();
}
