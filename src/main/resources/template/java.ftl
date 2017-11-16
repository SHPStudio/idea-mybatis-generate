package ${packageModel};

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
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

    public static QueryBuilder QueryBuild(){
        return new QueryBuilder();
    }

    public static class QueryBuilder extends ${className}{
        /**
        * 需要返回的列
        */
        private Map<String,Object> fetchFields;

        public Map<String,Object> getFetchFields(){
            return this.fetchFields;
        }

    <#list attrs as attr>
        private List<${attr.javaTypeName}> ${attr.columnName}List;

        <#if attr.isTime = "yes">
        private ${attr.javaTypeName} ${attr.columnName}St;

        private ${attr.javaTypeName} ${attr.columnName}Ed;

        public ${attr.javaTypeName} get${attr.columnName?cap_first}St(){
            return this.${attr.columnName}St;
        }

        public ${attr.javaTypeName} get${attr.columnName?cap_first}Ed(){
        return this.${attr.columnName}Ed;
        }
        </#if>
    </#list>
        private QueryBuilder (){
            this.fetchFields = new HashMap<>();
        }
        <#list attrs as attr>

        <#if attr.isTime = "yes">
        public QueryBuilder ${attr.columnName}BetWeen(${attr.javaTypeName} ${attr.columnName}St,${attr.javaTypeName} ${attr.columnName}Ed){
            this.${attr.columnName}St = ${attr.columnName}St;
            this.${attr.columnName}Ed = ${attr.columnName}Ed;
            return this;
        }
        </#if>

        public QueryBuilder ${attr.columnName}(${attr.javaTypeName} ${attr.columnName}){
            set${attr.columnName?cap_first}(${attr.columnName});
            return this;
        }

        public QueryBuilder ${attr.columnName}List(${attr.javaTypeName} ... ${attr.columnName}){
            this.${attr.columnName}List = Arrays.asList(${attr.columnName});
            return this;
        }

        public QueryBuilder ${attr.columnName}List(List<${attr.javaTypeName}> ${attr.columnName}){
            this.${attr.columnName}List = ${attr.columnName};
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
            this.fetchFields.put("AllFields",true);
            return this;
        }

        public QueryBuilder addField(String ... fields){
            this.fetchFields.put("otherFields",Arrays.asList(fields));
            return this;
        }
        @SuppressWarnings("unchecked")
        private void setFetchFields(String key,String val){
            Map<String,Boolean> fields= (Map<String, Boolean>) this.fetchFields.getOrDefault(key,new HashMap<>());
            fields.put(val,true);
            this.fetchFields.putIfAbsent(key,fields);
        }

        public ${className} build(){
            return this;
        }
    }
    //生成代码结束 don't delete

}
