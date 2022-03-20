package com.softdev.system.generator.exporter;

import java.io.IOException;
import java.util.Map;

public class DefaultFileExporter extends AbstractFileExporter {

    private final static DefaultFileExporter defaultFileExporter = new DefaultFileExporter();

    public static DefaultFileExporter getInstance() {
        return defaultFileExporter;
    }

    @Override
    public void writeToFile(Map<String, Object> params, String generatedCode) {
    }
}
