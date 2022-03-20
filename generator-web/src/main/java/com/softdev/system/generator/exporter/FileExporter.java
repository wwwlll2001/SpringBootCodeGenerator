package com.softdev.system.generator.exporter;

import java.io.IOException;
import java.util.Map;

public interface FileExporter {

    void writeToFile(Map<String, Object> params, String generatedCode) throws IOException;
}
