package com.softdev.system.generator.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.softdev.system.generator.entity.ClassInfo;
import com.softdev.system.generator.exporter.ExportCodeFile;
import com.softdev.system.generator.util.FreemarkerUtil;
import com.softdev.system.generator.util.MapUtil;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.softdev.system.generator.exporter.FileExporterFactory.getFileExporter;
import static com.softdev.system.generator.util.StringUtils.renderTemplateString;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * GeneratorService
 *
 * @author zhengkai.blog.csdn.net
 */
@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {

    String templateCpnfig = null;

    /**
     * 从项目中的JSON文件读取String
     *
     * @author zhengkai.blog.csdn.net
     */
    @Override
    public String getTemplateConfig() throws IOException {
        templateCpnfig = null;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template.json");
        templateCpnfig = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        inputStream.close();
        return templateCpnfig;
    }

    /**
     * 根据配置的Template模板进行遍历解析，得到生成好的String
     *
     * @author zhengkai.blog.csdn.net
     */
    @Override
    public Map<String, String> getResultByParams(Map<String, Object> params) throws IOException, TemplateException {
        Map<String, String> result = new HashMap<>(32);
        result.put("tableName", MapUtil.getString(params, "tableName"));

        JSONArray parentTemplates = JSONArray.parseArray(getTemplateConfig());
        for (int i = 0; i < parentTemplates.size(); i++) {
            JSONObject parentTemplateObj = parentTemplates.getJSONObject(i);
            for (int x = 0; x < parentTemplateObj.getJSONArray("templates").size(); x++) {
                JSONObject childTemplate = parentTemplateObj.getJSONArray("templates").getJSONObject(x);
                String generatedCode = FreemarkerUtil.processString(parentTemplateObj
                        .getString("group") + "/" + childTemplate.getString("name") + ".ftl", params);
                String templateName = childTemplate.getString("name");

                result.put(templateName, generatedCode);
                exportFile(params, childTemplate, generatedCode);
            }
        }
        return result;
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse httpServletResponse)
            throws IOException, TemplateException {
        List<ExportCodeFile> exportCodeFiles = getExportCodeFiles(params);
        export(exportCodeFiles, httpServletResponse);
    }

    private void export(List<ExportCodeFile> exportCodeFiles, HttpServletResponse httpServletResponse) {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            buildZipOutputStream(exportCodeFiles, out);
            export(httpServletResponse, out);
        } catch (IOException e) {
            throw new RuntimeException("export file error", e);
        }
    }

    private void export(HttpServletResponse httpServletResponse, ByteArrayOutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        byte[] bytes = out.toByteArray();

        httpServletResponse.setContentType("application/octet-stream;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + "code.zip");

        try (ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(in);
            while (bufferedInputStream1.read(buffer) != -1) {
                responseOutputStream.write(buffer);
            }
        }
    }

    private void buildZipOutputStream(List<ExportCodeFile> exportCodeFiles, ByteArrayOutputStream out)
            throws IOException {
        try (ZipOutputStream outputStream = new ZipOutputStream(out)) {
            for (ExportCodeFile exportCodeFile : exportCodeFiles) {
                boolean shouldExport = isNotEmpty(exportCodeFile.getPath()) && isNotEmpty(exportCodeFile.getName());
                if (shouldExport) {
                    ZipEntry zipEntry = new ZipEntry(exportCodeFile.getPath()
                            + System.getProperty("file.separator") + exportCodeFile.getName());
                    outputStream.putNextEntry(zipEntry);
                    outputStream.write(exportCodeFile.getContent().getBytes(StandardCharsets.UTF_8));
                    outputStream.closeEntry();
                }
            }
        }
    }

    private List<ExportCodeFile> getExportCodeFiles(Map<String, Object> params) throws IOException, TemplateException {
        String exportGroup = MapUtil.getString(params, "exportGroup");
        List<ExportCodeFile> exportCodeFiles = new ArrayList<>();

        Map<String, String> generateCodeMap = getResultByParams(params);
        String className = ((ClassInfo) params.get("classInfo")).getClassName();
        String basePackageName = MapUtil.getString(params, "packageName");

        JSONArray parentTemplates = JSONArray.parseArray(getTemplateConfig());
        for (int i = 0; i < parentTemplates.size(); i++) {
            JSONObject parentTemplateObj = parentTemplates.getJSONObject(i);
            boolean shouldExportThisGroup = Objects.equals(exportGroup, parentTemplateObj.getString("group"));

            if (shouldExportThisGroup) {
                for (int x = 0; x < parentTemplateObj.getJSONArray("templates").size(); x++) {
                    JSONObject childTemplate = parentTemplateObj.getJSONArray("templates").getJSONObject(x);
                    String templateName = childTemplate.getString("name");
                    String generatedCode = generateCodeMap.get(templateName);
                    ExportCodeFile exportCodeFile =
                            buildExportCodeFile(className, basePackageName, childTemplate, generatedCode, templateName);
                    exportCodeFiles.add(exportCodeFile);
                }
            }
        }

        return exportCodeFiles;
    }


    private ExportCodeFile buildExportCodeFile(String className,
                                               String basePackageName,
                                               JSONObject childTemplate,
                                               String generatedCode,
                                               String templateFileName) {
        String fileSubPath = childTemplate.getString("fileSubPath");
        String fileName = renderTemplateString(childTemplate.getString("fileNamePattern"),
                Map.of("resourceName", className));
        String path = basePackageName + System.getProperty("file.separator") + fileSubPath;
        String fileExporterClass = childTemplate.getString("fileExporterClass");

        return new ExportCodeFile(fileName, path, generatedCode, fileExporterClass, templateFileName);
    }

    private void exportFile(Map<String, Object> params, JSONObject childTemplate, String generatedCode) {
        Optional.ofNullable(childTemplate.getString("fileExporterClass"))
                .ifPresent(fileExporterClass -> {
                    try {
                        getFileExporter(fileExporterClass).writeToFile(params, generatedCode);
                    } catch (IOException e) {
                        throw new RuntimeException("write file error: ", e);
                    }
                });
    }
}
