package com.nit.dk.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;  
import org.springframework.stereotype.Repository;

import com.nit.dk.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{ 'fullName': { $regex: ?0, $options: 'i' } }")  
    List<Student> searchByName(String name);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);
}