package com.nit.dk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nit.dk.admin.dto.ChangePasswordRequest;
import com.nit.dk.admin.dto.LoginRequest;
import com.nit.dk.admin.dto.LoginResponse;
import com.nit.dk.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return adminService.login(request);
    }
    
    @PutMapping("/change-password")
    public LoginResponse changePassword(@RequestBody ChangePasswordRequest request) {
        return adminService.changePassword(request);
    }
}
