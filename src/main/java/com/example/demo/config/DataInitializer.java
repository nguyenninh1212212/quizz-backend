package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Role;
import com.example.demo.model.enums.ROLE;
import com.example.demo.repository.AuthRepo;
import com.example.demo.repository.RoleRepo;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepo RoleRepo;
    private final AuthRepo AuthRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepo RoleRepo, AuthRepo AuthRepo,
            PasswordEncoder passwordEncoder) {
        this.RoleRepo = RoleRepo;
        this.AuthRepo = AuthRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists(ROLE.ADMIN);
        createRoleIfNotExists(ROLE.MANAGER);
        createRoleIfNotExists(ROLE.CUSTOMER);

        // Tạo tài khoản admin nếu chưa có
        if (AuthRepo.findByUsername("admin").isEmpty()) {
            Role adminRole = RoleRepo.findByName(ROLE.ADMIN).orElseThrow();

            Auth admin = new Auth();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(adminRole);

            AuthRepo.save(admin);
            System.out.println("Tài khoản admin đã được tạo!");
        }
    }

    private void createRoleIfNotExists(ROLE roleName) {
        RoleRepo.findByName(roleName).orElseGet(() -> {
            Role role = new Role();
            role.setName(roleName);
            return RoleRepo.save(role);
        });
    }
}
