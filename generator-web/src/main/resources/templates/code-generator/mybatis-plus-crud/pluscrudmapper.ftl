<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.mapper;</#if>

<#if isAutoImport?exists && isAutoImport==true>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}.domain.${classInfo.className};
</#if>


public interface ${classInfo.className}Mapper extends BaseMapper<${classInfo.className}> {
}
