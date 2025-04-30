package com.example.demo.model.dto.Req.File;

import com.example.demo.model.enums.FILE;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ImageReqDTO extends FileBase {
    @Override
    public String getFileType() {
        return FILE.IMAGE.name().toLowerCase();
    }

}
