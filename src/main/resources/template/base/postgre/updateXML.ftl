
    <update id="update${className}">
        UPDATE ${sense}${tableName}${sense}
        SET
        <trim suffixOverrides=",">
        <#list attrs as attr>
            <#if attr.isAuto == "NO" && attr.isKey == 0>
            <if test="${attr.propertiesName} != null<#if attr.javaTypeName=="String"> and ${attr.propertiesName}!=''</#if>">
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.propertiesName}}::${attr.typeName},
            </if>
            </#if>
        </#list>
        </trim>
        WHERE
        <trim suffixOverrides="and">
        <#list attrs as attr>
            <#if attr.isKey == 1>
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.propertiesName}}::${attr.typeName} and
            </#if>
        </#list>
        </trim>
    </update>
