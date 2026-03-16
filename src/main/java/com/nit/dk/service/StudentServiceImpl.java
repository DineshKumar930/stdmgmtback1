package com.nit.dk.service;

import com.nit.dk.entity.Student;
import com.nit.dk.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(String id, Student studentDetails) {  // ✅ Long → String
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setFullName(studentDetails.getFullName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        student.setMobile(studentDetails.getMobile());
        student.setCity(studentDetails.getCity());

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String id) {  // ✅ Long → String
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    @Override
    public Student getStudentById(String id) {  // ✅ Long → String
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.searchByName(name);
    }

    @Override
    public boolean isEmailExists(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public boolean isMobileExists(String mobile) {
        return studentRepository.existsByMobile(mobile);
    }
}