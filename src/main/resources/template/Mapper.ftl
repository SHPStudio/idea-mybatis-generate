package ${packageMapper};

import java.util.List;
import ${packageModel}.${className};
/**
*  @author ${author}
*/
public interface ${className}Mapper {

    //生成代码开始 don't delete

    int insert(${className} object);

    int update(${className} object);

    List<${className}> query(${className} object);

    <#list attrs as attr>
        <#if attr.isKey == 1>
    ${className} getBy${attr.columnName?cap_first}(${attr.javaTypeName} ${attr.columnName?cap_first});
        </#if>
    </#list>
    //生成代码结束 don't delete
}