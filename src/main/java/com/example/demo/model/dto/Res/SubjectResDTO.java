package com.example.demo.model.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResDTO {
    private String id;
    private String school_id;
    private String name;

}
