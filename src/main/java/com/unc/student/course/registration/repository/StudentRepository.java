package com.unc.student.course.registration.repository;


import com.unc.student.course.registration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
