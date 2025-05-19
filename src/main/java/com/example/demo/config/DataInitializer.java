package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Role;
import com.example.demo.model.enums.ROLE;
import com.example.demo.repository.AuthRepo;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.specification.AuthSpeci;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists(ROLE.ADMIN);
        createRoleIfNotExists(ROLE.MANAGER);
        createRoleIfNotExists(ROLE.CUSTOMER);

        Specification<Auth> spec = Specification.where(AuthSpeci.hasUsername("admin")); // ✅ thêm dấu ;

        if (authRepo.findOne(spec).isEmpty()) { // ✅ dùng biến authRepo
            Role adminRole = roleRepo.findByName(ROLE.ADMIN).orElseThrow();

            Auth admin = new Auth();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(adminRole);

            authRepo.save(admin); // ✅ dùng biến authRepo
        }
    }

    private void createRoleIfNotExists(ROLE roleName) {
        roleRepo.findByName(roleName).orElseGet(() -> {
            Role role = new Role();
            role.setName(roleName);
            return roleRepo.save(role);
        });
    }
}
