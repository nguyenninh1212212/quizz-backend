package com.example.demo.model.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRes<T> {
    private T data;
    private Integer page;
    private Integer limit;
    private Integer totalPage;

}
