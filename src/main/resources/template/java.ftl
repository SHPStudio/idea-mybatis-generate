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

        <#if attr.isBetween = "yes">
        private ${attr.javaTypeName} ${attr.columnName}St;

        private ${attr.javaTypeName} ${attr.columnName}Ed;

        public ${attr.javaTypeName} get${attr.columnName?cap_first}St(){
            return this.${attr.columnName}St;
        }

        public ${attr.javaTypeName} get${attr.columnName?cap_first}Ed(){
            return this.${attr.columnName}Ed;
        }
        </#if>

        <#if attr.javaTypeName = "String">
        private List<${attr.javaTypeName}> fuzzy${attr.columnName?cap_first};

        public List<${attr.javaTypeName}> getFuzzy${attr.columnName?cap_first}(){
            return this.fuzzy${attr.columnName?cap_first};
        }

        private List<${attr.javaTypeName}> rightFuzzy${attr.columnName?cap_first};

        public List<${attr.javaTypeName}> getRightFuzzy${attr.columnName?cap_first}(){
            return this.rightFuzzy${attr.columnName?cap_first};
        }
        </#if>
    </#list>
        private QueryBuilder (){
            this.fetchFields = new HashMap<>();
        }
        <#list attrs as attr>

        <#if attr.isBetween = "yes">

        public QueryBuilder <#if attr.columnName?starts_with("set") || attr.columnName?starts_with("get")>with${attr.columnName?cap_first}<#else>${attr.columnName}</#if>BetWeen(${attr.javaTypeName} ${attr.columnName}St,${attr.javaTypeName} ${attr.columnName}Ed){
            this.${attr.columnName}St = ${attr.columnName}St;
            this.${attr.columnName}Ed = ${attr.columnName}Ed;
            return this;
        }

        public QueryBuilder <#if attr.columnName?starts_with("set") || attr.columnName?starts_with("get")>with${attr.columnName?cap_first}<#else>${attr.columnName}</#if>GreaterEqThan(${attr.javaTypeName} ${attr.columnName}St){
            this.${attr.columnName}St = ${attr.columnName}St;
            return this;
        }
        public QueryBuilder <#if attr.columnName?starts_with("set") || attr.columnName?starts_with("get")>with${attr.columnName?cap_first}<#else>${attr.columnName}</#if>LessEqThan(${attr.javaTypeName} ${attr.columnName}Ed){
            this.${attr.columnName}Ed = ${attr.columnName}Ed;
            return this;
        }
        </#if>

        <#if attr.javaTypeName = "String">
        public QueryBuilder fuzzy${attr.columnName?cap_first} (List<${attr.javaTypeName}> fuzzy${attr.columnName?cap_first}){
            this.fuzzy${attr.columnName?cap_first} = fuzzy${attr.columnName?cap_first};
            return this;
        }

        public QueryBuilder fuzzy${attr.columnName?cap_first} (${attr.javaTypeName} ... fuzzy${attr.columnName?cap_first}){
            this.fuzzy${attr.columnName?cap_first} = Arrays.asList(fuzzy${attr.columnName?cap_first});
            return this;
        }

        public QueryBuilder rightFuzzy${attr.columnName?cap_first} (List<${attr.javaTypeName}> rightFuzzy${attr.columnName?cap_first}){
            this.rightFuzzy${attr.columnName?cap_first} = rightFuzzy${attr.columnName?cap_first};
            return this;
        }

        public QueryBuilder rightFuzzy${attr.columnName?cap_first} (${attr.javaTypeName} ... rightFuzzy${attr.columnName?cap_first}){
            this.rightFuzzy${attr.columnName?cap_first} = Arrays.asList(rightFuzzy${attr.columnName?cap_first});
            return this;
        }
        </#if>

        public QueryBuilder <#if attr.columnName?starts_with("set") || attr.columnName?starts_with("get")>with${attr.columnName?cap_first}<#else>${attr.columnName}</#if>(${attr.javaTypeName} ${attr.columnName}){
            set${attr.columnName?cap_first}(${attr.columnName});
            return this;
        }

        public QueryBuilder <#if attr.columnName?starts_with("set") || attr.columnName?starts_with("get")>with${attr.columnName?cap_first}<#else>${attr.columnName}</#if>List(${attr.javaTypeName} ... ${attr.columnName}){
            this.${attr.columnName}List = Arrays.asList(${attr.columnName});
            return this;
        }

        public QueryBuilder <#if attr.columnName?starts_with("set") || attr.columnName?starts_with("get")>with${attr.columnName?cap_first}<#else>${attr.columnName}</#if>List(List<${attr.javaTypeName}> ${attr.columnName}){
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

}
