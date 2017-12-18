
    <insert id="insert${className}" <#if tableAttrs.autoKey??> useGeneratedKeys="true" keyProperty="${tableAttrs.autoKey }"</#if>>
        INSERT INTO ${tableName}
        (
        <trim suffixOverrides=",">
                <#list attrs as attr>
                    <if test="${attr.columnName}!=null">
                        ${attr.columnName},
                    </if>
                </#list>
        </trim>
        )
        VALUES
        (
        <trim suffixOverrides=",">
            <#list attrs as attr>
                <if test="${attr.columnName}!=null">
                    ${"#\{"}${attr.columnName}},
                </if>
            </#list>
        </trim>
        );
    </insert>
