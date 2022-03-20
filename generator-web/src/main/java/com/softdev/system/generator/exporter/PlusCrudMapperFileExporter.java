package com.softdev.system.generator.exporter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlusCrudMapperFileExporter extends AbstractFileExporter {

    private final static PlusCrudMapperFileExporter plusCrudMapperFileExporter
                                                                                  = new PlusCrudMapperFileExporter();

    public static PlusCrudMapperFileExporter getInstance() {
        return plusCrudMapperFileExporter;
    }

    @Override
    protected String getExportFileName(Map<String, Object> params) {
        return super.getExportFileName(params) + "Mapper.java";
    }

    @Override
    protected String getExportFilePath(Map<String, Object> params) {
        return super.getExportFilePath(params) + System.getProperty("file.separator") + "mapper";
    }
}
