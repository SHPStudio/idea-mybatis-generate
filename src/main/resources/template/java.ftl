package ${packageModel};
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author ${author}
*/
public class ${className} implements Serializable {

    private static final long serialVersionUID = ${timeStamp}L;

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
    private ${attr.javaTypeName} ${attr.propertiesName};
</#list>

<#list attrs as attr>

    public void set${attr.propertiesName?cap_first}(${attr.javaTypeName} ${attr.propertiesName}){
        this.${attr.propertiesName} = ${attr.propertiesName};
    }

    public ${attr.javaTypeName} get${attr.propertiesName?cap_first}(){
        return this.${attr.propertiesName};
    }
</#list>
    @Override
    public String toString() {
        return "${className}{" +
            <#list attrs as attr>
                "${attr.propertiesName}='" + ${attr.propertiesName} + '\'' +
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
        private List<${attr.javaTypeName}> ${attr.propertiesName}List;

        <#if attr.isBetween = "yes">
        private ${attr.javaTypeName} ${attr.propertiesName}St;

        private ${attr.javaTypeName} ${attr.propertiesName}Ed;

        public ${attr.javaTypeName} get${attr.propertiesName?cap_first}St(){
            return this.${attr.propertiesName}St;
        }

        public ${attr.javaTypeName} get${attr.propertiesName?cap_first}Ed(){
            return this.${attr.propertiesName}Ed;
        }
        </#if>

        <#if attr.javaTypeName = "String">
        private List<${attr.javaTypeName}> fuzzy${attr.propertiesName?cap_first};

        public List<${attr.javaTypeName}> getFuzzy${attr.propertiesName?cap_first}(){
            return this.fuzzy${attr.propertiesName?cap_first};
        }

        private List<${attr.javaTypeName}> rightFuzzy${attr.propertiesName?cap_first};

        public List<${attr.javaTypeName}> getRightFuzzy${attr.propertiesName?cap_first}(){
            return this.rightFuzzy${attr.propertiesName?cap_first};
        }
        </#if>
    </#list>
        private QueryBuilder (){
            this.fetchFields = new HashMap<>();
        }
        <#list attrs as attr>

        <#if attr.isBetween = "yes">

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>BetWeen(${attr.javaTypeName} ${attr.propertiesName}St,${attr.javaTypeName} ${attr.propertiesName}Ed){
            this.${attr.propertiesName}St = ${attr.propertiesName}St;
            this.${attr.propertiesName}Ed = ${attr.propertiesName}Ed;
            return this;
        }

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>GreaterEqThan(${attr.javaTypeName} ${attr.propertiesName}St){
            this.${attr.propertiesName}St = ${attr.propertiesName}St;
            return this;
        }
        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>LessEqThan(${attr.javaTypeName} ${attr.propertiesName}Ed){
            this.${attr.propertiesName}Ed = ${attr.propertiesName}Ed;
            return this;
        }
        </#if>

        <#if attr.javaTypeName = "String">
        public QueryBuilder fuzzy${attr.propertiesName?cap_first} (List<${attr.javaTypeName}> fuzzy${attr.propertiesName?cap_first}){
            this.fuzzy${attr.propertiesName?cap_first} = fuzzy${attr.propertiesName?cap_first};
            return this;
        }

        public QueryBuilder fuzzy${attr.propertiesName?cap_first} (${attr.javaTypeName} ... fuzzy${attr.propertiesName?cap_first}){
            if (fuzzy${attr.propertiesName?cap_first} != null){
                List<${attr.javaTypeName}> list = new ArrayList<>();
                for (${attr.javaTypeName} item : fuzzy${attr.propertiesName?cap_first}){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.fuzzy${attr.propertiesName?cap_first} = list;
            }
            return this;
        }

        public QueryBuilder rightFuzzy${attr.propertiesName?cap_first} (List<${attr.javaTypeName}> rightFuzzy${attr.propertiesName?cap_first}){
            this.rightFuzzy${attr.propertiesName?cap_first} = rightFuzzy${attr.propertiesName?cap_first};
            return this;
        }

        public QueryBuilder rightFuzzy${attr.propertiesName?cap_first} (${attr.javaTypeName} ... rightFuzzy${attr.propertiesName?cap_first}){
            if (rightFuzzy${attr.propertiesName?cap_first} != null){
                List<${attr.javaTypeName}> list = new ArrayList<>();
                for (${attr.javaTypeName} item : rightFuzzy${attr.propertiesName?cap_first}){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.rightFuzzy${attr.propertiesName?cap_first} = list;
            }
            return this;
        }
        </#if>

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>(${attr.javaTypeName} ${attr.propertiesName}){
            set${attr.propertiesName?cap_first}(${attr.propertiesName});
            return this;
        }

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>List(${attr.javaTypeName} ... ${attr.propertiesName}){
            if (${attr.propertiesName} != null){
                List<${attr.javaTypeName}> list = new ArrayList<>();
                for (${attr.javaTypeName} item : ${attr.propertiesName}){
                    if (item != null){
                        list.add(item);
                    }
                }
                this.${attr.propertiesName}List = list;
            }

            return this;
        }

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>List(List<${attr.javaTypeName}> ${attr.propertiesName}){
            this.${attr.propertiesName}List = ${attr.propertiesName};
            return this;
        }

        public QueryBuilder fetch${attr.propertiesName?cap_first}(){
            setFetchFields("fetchFields","${attr.propertiesName}");
            return this;
        }

        public QueryBuilder exclude${attr.propertiesName?cap_first}(){
            setFetchFields("excludeFields","${attr.propertiesName}");
            return this;
        }

        </#list>
        public QueryBuilder fetchAll(){
            this.fetchFields.put("AllFields",true);
            return this;
        }

        public QueryBuilder addField(String ... fields){
            List<String> list = new ArrayList<>();
            if (fields != null){
                for (String field : fields){
                    list.add(field);
                }
            }
            this.fetchFields.put("otherFields",list);
            return this;
        }
        @SuppressWarnings("unchecked")
        private void setFetchFields(String key,String val){
            Map<String,Boolean> fields= (Map<String, Boolean>) this.fetchFields.get(key);
            if (fields == null){
                fields = new HashMap<>();
            }
            fields.put(val,true);
            this.fetchFields.put(key,fields);
        }

        public ${className} build(){
            return this;
        }
    }

}
