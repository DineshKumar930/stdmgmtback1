package com.nit.dk.repo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.nit.dk.entity.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    
    @Query("{ 'username': ?0 }")  
    Optional<Admin> findByUsername(String username);
}