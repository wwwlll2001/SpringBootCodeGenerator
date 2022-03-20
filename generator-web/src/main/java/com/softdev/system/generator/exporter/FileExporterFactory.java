package com.softdev.system.generator.exporter;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class FileExporterFactory {

    public static FileExporter getFileExporter(String fileExporterClassName) {
            Class<?> exporterClass = null;
            try {
                exporterClass = Class.forName(fileExporterClassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                return (FileExporter) Objects.requireNonNull(exporterClass)
                                             .getMethod("getInstance")
                                             .invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("export file error:", e);
            }
    }
}
