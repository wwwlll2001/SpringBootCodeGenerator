<#if isWithPackage?exists && isWithPackage==true>package ${packageName}.api;</#if>

import ${packageName}.dto.${classInfo.className}Dto;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "${classInfo.className} endpoint", tags = "${classInfo.className} demo")
public interface ${classInfo.className}Api {

    @ApiOperation(value = "create ${classInfo.className}")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "x-request-id", value = "${r'${clientTraceId}'}",
                    paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = "Authorization", value = "${r'${BearerToken}'}",
                    paramType = "header", dataType = "String", required = false)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Authentication Error"),
            @ApiResponse(code = 403, message = "Authorization Error"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 503, message = "Service Unavailable")
    })
    Long create${classInfo.className}(@RequestBody ${classInfo.className}Dto ${classInfo.className?uncap_first});

    @ApiOperation(value = "get ${classInfo.className}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "x-request-id", value = "${r'${clientTraceId}'}",
            paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = "Authorization", value = "${r'${BearerToken}'}",
            paramType = "header", dataType = "String", required = false)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Authentication Error"),
            @ApiResponse(code = 403, message = "Authorization Error"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 503, message = "Service Unavailable")
    })
    ${classInfo.className}Dto read${classInfo.className}ById(String id);

    @ApiOperation(value = "update ${classInfo.className}")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "x-request-id", value = "${r'${clientTraceId}'}",
            paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = "Authorization", value = "${r'${BearerToken}'}",
            paramType = "header", dataType = "String", required = false)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Authentication Error"),
            @ApiResponse(code = 403, message = "Authorization Error"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 503, message = "Service Unavailable")
    })
    Long update${classInfo.className}(@RequestBody ${classInfo.className}Dto ${classInfo.className});

    @ApiOperation(value = "delete ${classInfo.className}")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "x-request-id", value = "${r'${clientTraceId}'}",
            paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = "Authorization", value = "${r'${BearerToken}'}",
            paramType = "header", dataType = "String", required = false)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Authentication Error"),
            @ApiResponse(code = 403, message = "Authorization Error"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 503, message = "Service Unavailable")
    })
    boolean delete${classInfo.className}ById(String id);
}
