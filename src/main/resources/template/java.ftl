package ${packageModel};

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
*
*  @author ${author}
*/
public class ${className} {

    //生成代码开始 don't delete

<#list attrs as attr>

    <#if attr.remarks!="" || attr.nullAble?? ||attr.columnDef??>
    /**
    <#if attr.isKey == 1>
    * 主键
    </#if>
    * ${attr.remarks}
    * isNullAble:${attr.nullAble}<#if attr.columnDef??>,defaultVal:${attr.columnDef}</#if>
    */
    </#if>
    private ${attr.javaTypeName} ${attr.columnName};
</#list>

<#list attrs as attr>

    public void set${attr.columnName?cap_first}(${attr.javaTypeName} ${attr.columnName}){
        this.${attr.columnName} = ${attr.columnName};
    }

    public ${attr.javaTypeName} get${attr.columnName?cap_first}(){
        return this.${attr.columnName};
    }
</#list>
    @Override
    public String toString() {
        return "${className}{" +
            <#list attrs as attr>
                "${attr.columnName}='" + ${attr.columnName} + '\'' +
            </#list>
            '}';
    }

    /**
    * 需要返回的列
    */
    private Map<String,Object> fetchFields;

    public Map<String,Object> getFetchFields(){
        return this.fetchFields;
    }

    public static QueryBuilder QueryBuild(){
        return new QueryBuilder();
    }

    public static class QueryBuilder{

        private ${className} object ;

        private QueryBuilder (){
            this.object = new ${className} ();
            this.object.fetchFields = new HashMap<>();
        }
        <#list attrs as attr>
        public QueryBuilder ${attr.columnName}(${attr.javaTypeName} ${attr.columnName}){
            this.object.${attr.columnName} = ${attr.columnName};
            return this;
        }

        public QueryBuilder fetch${attr.columnName?cap_first}(){
            setFetchFields("fetchFields","${attr.columnName}");
            return this;
        }

        public QueryBuilder exclude${attr.columnName?cap_first}(){
            setFetchFields("excludeFields","${attr.columnName}");
            return this;
        }

        </#list>
        public QueryBuilder fetchAll(){
            this.object.fetchFields.put("AllFields",true);
            return this;
        }

        public QueryBuilder addField(String ... fields){
            this.object.fetchFields.put("otherFields",Arrays.asList(fields));
            return this;
        }
        @SuppressWarnings("unchecked")
        private void setFetchFields(String key,String val){
            Map<String,Boolean> fields= (Map<String, Boolean>) this.object.fetchFields.getOrDefault(key,new HashMap<>());
            fields.put(val,true);
            this.object.fetchFields.putIfAbsent(key,fields);
        }

        public ${className} build(){
            return this.object;
        }
    }
    //生成代码结束 don't delete

}
