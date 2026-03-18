package com.nit.dk.controller;

import com.nit.dk.entity.Student;
import com.nit.dk.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")

public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    
    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        Map<String, String> errors = new HashMap<>();
        
        if (studentService.isEmailExists(student.getEmail())) {
            errors.put("email", "Email already exists!");
        }
        
        if (studentService.isMobileExists(student.getMobile())) {
            errors.put("mobile", "Mobile number already exists!");
        }
        
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        Map<String, String> errors = new HashMap<>();
        
        Student existingStudent = studentService.getStudentById(id);
        
        if (!existingStudent.getEmail().equals(student.getEmail()) && 
            studentService.isEmailExists(student.getEmail())) {
            errors.put("email", "Email already exists!");
        }
        
        if (!existingStudent.getMobile().equals(student.getMobile()) && 
            studentService.isMobileExists(student.getMobile())) {
            errors.put("mobile", "Mobile number already exists!");
        }
        
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String name) {
        List<Student> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }
}
