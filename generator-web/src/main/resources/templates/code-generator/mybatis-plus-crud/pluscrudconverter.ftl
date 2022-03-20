<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.converter;</#if>

<#if isAutoImport?exists && isAutoImport==true>
import ${packageName}.dto.${classInfo.className}Dto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
</#if>

@Mapper
public interface ${classInfo.className}Converter {

    ${classInfo.className}Converter INSTANCE = Mappers.getMapper(${classInfo.className}Converter.class);

    ${classInfo.className}Dto convertTo${classInfo.className}Dto(${classInfo.className} ${classInfo.className?uncap_first});

    ${classInfo.className} convertTo${classInfo.className}(${classInfo.className}Dto ${classInfo.className?uncap_first}Dto);
}
