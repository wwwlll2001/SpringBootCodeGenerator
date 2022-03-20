package com.softdev.system.generator.service;

import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * GeneratorService
 *
 * @author zhengkai.blog.csdn.net
 */
public interface GeneratorService {

    String getTemplateConfig() throws IOException;

    Map<String, String> getResultByParams(Map<String, Object> params) throws IOException, TemplateException;

    void export(Map<String, Object> params, HttpServletResponse httpServletResponse)
            throws IOException, TemplateException;

}
