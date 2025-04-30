package com.example.demo.model.dto.Req.File;

import com.example.demo.model.enums.FILE;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class RawReqDTO extends FileBase {
    @Override
    public String getFileType() {
        return FILE.RAW.name().toLowerCase();
    }

}
