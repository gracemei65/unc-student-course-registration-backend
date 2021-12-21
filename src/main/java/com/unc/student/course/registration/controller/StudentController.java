package com.unc.student.course.registration.controller;


import com.unc.student.course.registration.domain.Student;
import com.unc.student.course.registration.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) throws URISyntaxException {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.created(new URI("/students/" + savedStudent.getId())).body(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student currentStudent = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        currentStudent.setName(student.getName());
        currentStudent.setEmail(student.getEmail());
        currentStudent = studentRepository.save(student);

        return ResponseEntity.ok(currentStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
