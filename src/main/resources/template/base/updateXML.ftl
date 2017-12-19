
    <update id="update${className}">
        UPDATE ${tableName}
        SET
        <trim suffixOverrides=",">
        <#list attrs as attr>
            <#if attr.isAuto == "NO" && attr.isKey == 0>
            <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
                `${attr.columnName}` = ${"#\{"}${attr.columnName}},
            </if>
            </#if>
        </#list>
        </trim>
        WHERE
        <trim suffixOverrides="and">
        <#list attrs as attr>
            <#if attr.isKey == 1>
                `${attr.columnName}` = ${"#\{"}${attr.columnName}} and
            </#if>
        </#list>
        </trim>
    </update>
