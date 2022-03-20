package com.softdev.system.generator.exporter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlusCrudEntityFileExporter extends AbstractFileExporter {

    private final static PlusCrudEntityFileExporter plusCrudEntityFileExporter = new PlusCrudEntityFileExporter();

    public static PlusCrudEntityFileExporter getInstance() {
        return plusCrudEntityFileExporter;
    }

    @Override
    protected String getExportFileName(Map<String, Object> params) {
        return super.getExportFileName(params) + ".java";
    }

    @Override
    protected String getExportFilePath(Map<String, Object> params) {
        return super.getExportFilePath(params) + System.getProperty("file.separator") + "domain";
    }
}
