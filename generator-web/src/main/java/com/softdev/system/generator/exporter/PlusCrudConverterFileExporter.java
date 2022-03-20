package com.softdev.system.generator.exporter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlusCrudConverterFileExporter extends AbstractFileExporter {

    private final static PlusCrudConverterFileExporter plusCrudConverterFileExporter
                                                                                  = new PlusCrudConverterFileExporter();

    public static PlusCrudConverterFileExporter getInstance() {
        return plusCrudConverterFileExporter;
    }

    @Override
    protected String getExportFileName(Map<String, Object> params) {
        return super.getExportFileName(params) + "Converter.java";
    }

    @Override
    protected String getExportFilePath(Map<String, Object> params) {
        return super.getExportFilePath(params) + System.getProperty("file.separator") + "domain";
    }
}
