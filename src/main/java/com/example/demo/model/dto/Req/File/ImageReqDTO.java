package com.example.demo.model.dto.Req.File;

import com.example.demo.model.enums.FILE;

import lombok.Setter;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class ImageReqDTO extends FileBase {
    @Override
    public String getFileType() {
        return FILE.IMAGE.name().toLowerCase();
    }

}
