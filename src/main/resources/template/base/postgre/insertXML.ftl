
    <insert id="insert${className}" <#if tableAttrs.autoKey??> useGeneratedKeys="true" keyProperty="${tableAttrs.autoKey}"</#if>>
        INSERT INTO ${sense}${tableName}${sense}
        (
        <trim suffixOverrides=",">
                <#list attrs as attr>
                    <if test="${attr.columnName}!=null">
                        ${sense}${attr.columnName}${sense},
                    </if>
                </#list>
        </trim>
        )
        VALUES
        (
        <trim suffixOverrides=",">
            <#list attrs as attr>
                <if test="${attr.columnName}!=null">
                    ${"#\{"}${attr.columnName}}::${attr.typeName},
                </if>
            </#list>
        </trim>
        );
    </insert>
