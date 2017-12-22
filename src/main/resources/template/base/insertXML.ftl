
    <insert id="insert${className}" <#if tableAttrs.autoKey??> useGeneratedKeys="true" keyProperty="${tableAttrs.autoKey }"</#if>>
        INSERT INTO ${tableName}
        (
        <trim suffixOverrides=",">
                <#list attrs as attr>
                    <if test="${attr.columnName}!=null">
                        `${attr.columnName}`,
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

    <insert id="insert${className}List" <#if tableAttrs.autoKey??> useGeneratedKeys="true" keyProperty="${tableAttrs.autoKey }"</#if>>
        INSERT INTO ${tableName}
        (
        <trim suffixOverrides=",">
        <#list attrs as attr>
        <if test="list.get(0).${attr.columnName} !=null" >
            `${attr.columnName}`,
        </if>
        </#list>
        </trim>
        )
        VALUES
        <foreach collection="list" separator="," item="item">
            (
            <trim suffixOverrides=",">
            <#list attrs as attr>
            <if test="list.get(0).${attr.columnName} !=null" >
                ${"#\{item."}${attr.columnName}},
            </if>
            </#list>
            </trim>
            )
        </foreach>
        ;
    </insert>
