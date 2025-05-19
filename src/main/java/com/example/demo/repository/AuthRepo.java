package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Auth;

@Repository
public interface AuthRepo extends JpaRepository<Auth, String>, JpaSpecificationExecutor<Auth> {

}
