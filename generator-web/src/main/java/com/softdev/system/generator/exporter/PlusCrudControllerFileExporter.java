package com.softdev.system.generator.exporter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlusCrudControllerFileExporter extends AbstractFileExporter {

    private final static PlusCrudControllerFileExporter plusCrudControllerFileExporter
                                                                                 = new PlusCrudControllerFileExporter();

    public static PlusCrudControllerFileExporter getInstance() {
        return plusCrudControllerFileExporter;
    }

    @Override
    protected String getExportFileName(Map<String, Object> params) {
        return super.getExportFileName(params) + "Controller.java";
    }

    @Override
    protected String getExportFilePath(Map<String, Object> params) {
        return super.getExportFilePath(params) + System.getProperty("file.separator") + "controller";
    }
}
