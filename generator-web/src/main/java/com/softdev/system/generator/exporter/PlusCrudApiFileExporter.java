package com.softdev.system.generator.exporter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlusCrudApiFileExporter extends AbstractFileExporter {

    private final static PlusCrudApiFileExporter plusCrudApiFileExporter = new PlusCrudApiFileExporter();

    public static PlusCrudApiFileExporter getInstance() {
        return plusCrudApiFileExporter;
    }

    @Override
    protected String getExportFileName(Map<String, Object> params) {
        return super.getExportFileName(params) + "Api.java";
    }

    @Override
    protected String getExportFilePath(Map<String, Object> params) {
        return super.getExportFilePath(params) + System.getProperty("file.separator") + "api";
    }
}
