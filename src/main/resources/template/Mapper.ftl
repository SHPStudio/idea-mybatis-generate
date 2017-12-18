package ${packageMapper};

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ${packageModel}.${className};
/**
*  @author ${author}
*/
public interface ${mapperName}Mapper {

    //生成代码开始 don't delete
    <#include "base/insertMapper.ftl">

    <#include "base/updateMapper.ftl">

    <#include "base/selectMapper.ftl">


    //生成代码结束 don't delete
}