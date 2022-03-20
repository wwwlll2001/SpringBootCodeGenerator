package com.softdev.system.generator.exporter;

import com.softdev.system.generator.entity.ClassInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import static java.util.Collections.singleton;

public class AbstractFileExporter implements FileExporter {

    @Override
    public void writeToFile(Map<String, Object> params, String generatedCode) throws IOException {
        String exportFileName = this.getExportFileName(params);
        String exportFilePath = this.getExportFilePath(params);

        Path path = Path.of(exportFilePath, exportFileName);
        Files.createDirectories(path.getParent());
        Files.write(path, singleton(generatedCode), StandardCharsets.UTF_8);
    }

    protected String getExportFileName(Map<String, Object> params) {
        return ((ClassInfo) params.get("classInfo")).getClassName();
    }

    protected String getExportFilePath(Map<String, Object> params) {
        return ((String) params.get("packageName")).replaceAll("\\.", System.getProperty("file.separator"));
    }
}
