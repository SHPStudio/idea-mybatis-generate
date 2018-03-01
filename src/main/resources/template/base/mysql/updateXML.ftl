
    <update id="update${className}">
        UPDATE ${sense}${tableName}${sense}
        SET
        <trim suffixOverrides=",">
        <#list attrs as attr>
            <#if attr.isAuto == "NO" && attr.isKey == 0>
            <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.columnName}},
            </if>
            </#if>
        </#list>
        </trim>
        WHERE
        <trim suffixOverrides="and">
        <#list attrs as attr>
            <#if attr.isKey == 1>
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.columnName}} and
            </#if>
        </#list>
        </trim>
    </update>
