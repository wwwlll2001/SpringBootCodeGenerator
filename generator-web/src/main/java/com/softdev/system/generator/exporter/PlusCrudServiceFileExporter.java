package com.softdev.system.generator.exporter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlusCrudServiceFileExporter extends AbstractFileExporter {

    private final static PlusCrudServiceFileExporter plusCrudServiceFileExporter
                                                                                  = new PlusCrudServiceFileExporter();

    public static PlusCrudServiceFileExporter getInstance() {
        return plusCrudServiceFileExporter;
    }

    @Override
    protected String getExportFileName(Map<String, Object> params) {
        return super.getExportFileName(params) + "Service.java";
    }

    @Override
    protected String getExportFilePath(Map<String, Object> params) {
        return super.getExportFilePath(params) + System.getProperty("file.separator") + "service";
    }
}
