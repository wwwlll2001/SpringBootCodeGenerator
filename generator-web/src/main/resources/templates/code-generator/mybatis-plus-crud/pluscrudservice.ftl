<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.service;</#if>

<#if isAutoImport?exists && isAutoImport==true>
import ${packageName}.converter.${classInfo.className}Converter;
import ${packageName}.domain.${classInfo.className};
import ${packageName}.dto.${classInfo.className}Dto;
import ${packageName}.mapper.${classInfo.className}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
</#if>

@Service
public class ${classInfo.className}Service {

    @Autowired
    private ${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;
    
    private ${classInfo.className}Converter ${classInfo.className?uncap_first}Converter = ${classInfo.className}Converter.INSTANCE;

    public Long create${classInfo.className}(${classInfo.className}Dto ${classInfo.className?uncap_first}Dto) {
        ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Converter.convertTo${classInfo.className}(${classInfo.className?uncap_first}Dto);
        int insertCount = ${classInfo.className?uncap_first}Mapper.insert(${classInfo.className?uncap_first});
        return insertCount > 0 ? ${classInfo.className?uncap_first}.getId() : null;
    }

    public ${classInfo.className}Dto get${classInfo.className}ById(String id) {
        ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Mapper.selectById(id);
        return ${classInfo.className?uncap_first}Converter.convertTo${classInfo.className}Dto(${classInfo.className?uncap_first});
    }

    public Long update${classInfo.className}(${classInfo.className}Dto ${classInfo.className?uncap_first}Dto) {
        ${classInfo.className} ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Converter.convertTo${classInfo.className}(${classInfo.className?uncap_first}Dto);
        return ${classInfo.className?uncap_first}Mapper.updateById(${classInfo.className?uncap_first}) > 0 ? ${classInfo.className?uncap_first}.getId() : null;
    }

    public boolean delete${classInfo.className}ById(String id) {
        return ${classInfo.className?uncap_first}Mapper.deleteById(id) > 0;
    }
}
