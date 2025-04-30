package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Role;
import com.example.demo.model.enums.ROLE;

@Repository
public interface RoleRepo extends JpaRepository<Role, ROLE>, JpaSpecificationExecutor<Role> {
    @Query("SELECT s FROM Role s WHERE s.name =:name ")
    Optional<Role> findByName(ROLE name);
}
