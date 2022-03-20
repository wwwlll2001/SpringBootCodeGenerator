package com.softdev.system.generator.exporter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportCodeFile {

    private String name;
    private String path;
    private String content;
    private String fileExporterClass;
    private String templateName;
}
