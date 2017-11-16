package ${packageMapper};

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ${packageModel}.${className};
/**
*  @author ${author}
*/
public interface ${className}Mapper {

    //生成代码开始 don't delete

    int insert${className}(${className} object);

    int update${className}(${className} object);

    List<${className}> query${className}(${className} object);

    //生成代码结束 don't delete
}