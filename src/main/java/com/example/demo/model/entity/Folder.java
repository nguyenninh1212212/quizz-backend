package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Folder extends Base {
    private String name;
    @ManyToOne
    private Auth auth;
    @OneToMany
    private List<Save> save;
}
