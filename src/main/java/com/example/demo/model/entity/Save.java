package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Save extends Base {
    @ManyToOne
    private Exam exam;
    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;
    @ManyToOne
    @JoinColumn(name = "auth_id")
    private Auth auth;
}
