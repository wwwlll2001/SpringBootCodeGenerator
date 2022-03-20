<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.controller;</#if>
<#if isAutoImport?exists && isAutoImport==true>

import ${packageName}.api.${classInfo.className}Api;
import ${packageName}.dto.${classInfo.className}Dto;
import ${packageName}.service.${classInfo.className}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
</#if>

@RestController
@RequestMapping("/${restResourceName}")
public class ${classInfo.className}Controller implements ${classInfo.className}Api {

    @Autowired
    private ${classInfo.className}Service ${classInfo.className?uncap_first}Service;

    @PostMapping
    public Long create${classInfo.className}(@RequestBody ${classInfo.className}Dto ${classInfo.className?uncap_first}Dto){
        return ${classInfo.className?uncap_first}Service.create${classInfo.className}(${classInfo.className?uncap_first}Dto);
    }

    @GetMapping("/{id}")
    public ${classInfo.className}Dto read${classInfo.className}ById(@PathVariable(value = "id") String id) {
        return ${classInfo.className?uncap_first}Service.get${classInfo.className}ById(id);
    }

    @PutMapping
    public Long update${classInfo.className}(@RequestBody ${classInfo.className}Dto ${classInfo.className?uncap_first}Dto) {
        return ${classInfo.className?uncap_first}Service.update${classInfo.className}(${classInfo.className?uncap_first}Dto);
    }

    @DeleteMapping("/{id}")
    public boolean delete${classInfo.className}ById(@PathVariable(value = "id") String id) {
        return ${classInfo.className?uncap_first}Service.delete${classInfo.className}ById(id);
    }
}



