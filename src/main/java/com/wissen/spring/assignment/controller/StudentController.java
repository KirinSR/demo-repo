package com.wissen.spring.assignment.controller;

import com.wissen.spring.assignment.entity.Student;
import com.wissen.spring.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{rollNo}")
    public Object getStudentByRollNo(@PathVariable Long rollNo) {
        Optional<Student> student = studentRepository.findById(rollNo);
        if (student.isPresent()) {
            return student.get();
        } else {
            return "Student with rollNo " + rollNo + " not found";
        }
    }

    @PostMapping
    public String createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return "Student created successfully";
    }

    @PutMapping("/{rollNo}")
    public String updateStudent(@PathVariable Long rollNo, @RequestBody Student studentDetails) {
        Optional<Student> student = studentRepository.findById(rollNo);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setName(studentDetails.getName());
            existingStudent.setStandard(studentDetails.getStandard());
            existingStudent.setSchool(studentDetails.getSchool());
            existingStudent.setPercentage(studentDetails.getPercentage());
            studentRepository.save(existingStudent);
            return "Student updated successfully";
        } else {
            return "Student with rollNo " + rollNo + " not found";
        }
    }

    @DeleteMapping("/{rollNo}")
    public String deleteStudent(@PathVariable Long rollNo) {
        Optional<Student> student = studentRepository.findById(rollNo);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return "Student deleted successfully";
        } else {
            return "Student with rollNo " + rollNo + " not found";
        }
    }

    @GetMapping("/result/pass=true")
    public List<Student> getPassingStudents() {
        return studentRepository.findByPercentageGreaterThan(40.0);
    }

    @GetMapping("/toppers")
    public List<Student> getToppers() {
        return studentRepository.findTop3ByOrderByPercentageDesc();
    }

    @GetMapping("/strength")
    public List<Student> getStudentsByStandard(@RequestParam String standard) {
        return studentRepository.findByStandard(standard);
    }

    @GetMapping("/total")
    public List<Student> getStudentsBySchool(@RequestParam String school) {
        return studentRepository.findBySchool(school);
    }
}
