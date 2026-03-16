package com.nit.dk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.nit.dk.admin.dto.ChangePasswordRequest;
import com.nit.dk.admin.dto.LoginRequest;
import com.nit.dk.admin.dto.LoginResponse;
import com.nit.dk.entity.Admin;
import com.nit.dk.repo.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public LoginResponse login(LoginRequest request) {
        System.out.println("🔍 Username: " + request.getUsername());
        System.out.println("🔍 Password: " + request.getPassword());
        System.out.println("🔍 DB Name: " + mongoTemplate.getDb().getName());
        // ✅ Sabhi admins print karo
        List<Admin> allAdmins = adminRepository.findAll();
        System.out.println("🔍 Total admins in DB: " + allAdmins.size());
        for(Admin a : allAdmins) {
            System.out.println("🔍 DB Admin -> username: " + a.getUsername() + " | password: " + a.getPassword());
        }

        Optional<Admin> adminOptional = adminRepository.findByUsername(request.getUsername());
        System.out.println("🔍 Admin found: " + adminOptional.isPresent());

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (admin.getPassword().equals(request.getPassword())) {
                return new LoginResponse(true, "Admin login successful");
            } else {
                return new LoginResponse(false, "Invalid password");
            }
        } else {
            return new LoginResponse(false, "Admin not found");
        }
    }

    public LoginResponse changePassword(ChangePasswordRequest request) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(request.getUsername());
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (!admin.getPassword().equals(request.getOldPassword())) {
                return new LoginResponse(false, "Old password is incorrect");
            }
            admin.setPassword(request.getNewPassword());
            adminRepository.save(admin);
            return new LoginResponse(true, "Password changed successfully");
        } else {
            return new LoginResponse(false, "Admin not found");
        }
    }
    
    
}