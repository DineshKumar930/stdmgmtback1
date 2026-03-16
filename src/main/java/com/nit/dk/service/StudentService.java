package com.nit.dk.service;

import com.nit.dk.entity.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(String id, Student studentDetails);  // ✅ String
    void deleteStudent(String id);                             // ✅ String
    Student getStudentById(String id);                        // ✅ String
    List<Student> getAllStudents();
    List<Student> searchStudentsByName(String name);
    boolean isEmailExists(String email);
    boolean isMobileExists(String mobile);
}