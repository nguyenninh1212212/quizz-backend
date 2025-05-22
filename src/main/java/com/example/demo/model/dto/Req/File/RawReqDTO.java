package com.example.demo.model.dto.Req.File;

import com.example.demo.model.enums.FILE;

import lombok.Setter;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class RawReqDTO extends FileBase {
    @Override
    public String getFileType() {
        return FILE.RAW.name().toLowerCase();
    }

}
