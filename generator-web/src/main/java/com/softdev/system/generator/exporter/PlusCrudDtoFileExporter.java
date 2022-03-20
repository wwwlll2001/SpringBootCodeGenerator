package com.softdev.system.generator.exporter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlusCrudDtoFileExporter extends AbstractFileExporter {

    private final static PlusCrudDtoFileExporter plusCrudDtoFileExporter = new PlusCrudDtoFileExporter();

    public static PlusCrudDtoFileExporter getInstance() {
        return plusCrudDtoFileExporter;
    }

    @Override
    protected String getExportFileName(Map<String, Object> params) {
        return super.getExportFileName(params) + "Dto.java";
    }

    @Override
    protected String getExportFilePath(Map<String, Object> params) {
        return super.getExportFilePath(params) + System.getProperty("file.separator") + "dto";
    }
}
