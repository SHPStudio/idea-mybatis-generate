
    <select id="query${className}" resultType="${packageModel}.${className}">
        select
        <include refid="baseResult"></include>
        from  ${tableName}
        <trim prefix="where" suffixOverrides="and | or">
                <#list attrs as attr>
                <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
                    ${attr.columnName} = ${"#\{"}${attr.columnName}} and
                </if>
                </#list>
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">
                <#list attrs as attr>
                    <if test="${attr.columnName}List != null">
                        ${attr.columnName} in
                        <foreach collection="${attr.columnName}List" close=")" open="(" separator="," item="item">
                            ${"#\{"}item}
                        </foreach> and
                    </if>
            <#if attr.isTime = "yes">
                <if test="${attr.columnName}St !=null and ${attr.columnName}Ed!=null">
                    (${attr.columnName} >= ${"#\{"}${attr.columnName}St} and ${attr.columnName} &lt; ${"#\{"}${attr.columnName}Ed}) and
                </if>
            </#if>
                </#list>
            </if>
        </trim>
    </select>

    <select id="query${className}Limit1" resultType="${packageModel}.${className}">
        select
        <include refid="baseResult"></include>
        from  ${tableName}
        <trim prefix="where" suffixOverrides="and | or">
            <#list attrs as attr>
            <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
                ${attr.columnName} = ${"#\{"}${attr.columnName}} and
            </if>
            </#list>
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">
            <#list attrs as attr>
                <if test="${attr.columnName}List != null">
                    ${attr.columnName} in
                    <foreach collection="${attr.columnName}List" close=")" open="(" separator="," item="item">
                        ${"#\{"}item}
                    </foreach> and
                </if>
        <#if attr.isTime = "yes">
            <if test="${attr.columnName}St !=null and ${attr.columnName}Ed!=null">
                (${attr.columnName} >= ${"#\{"}${attr.columnName}St} and ${attr.columnName} &lt; ${"#\{"}${attr.columnName}Ed}) and
            </if>
        </#if>
            </#list>
            </if>
        </trim>
        limit 1
    </select>

    <sql id="baseResult">
        <trim suffixOverrides=",">
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">

                <if test="fetchFields==null">
                    *,
                </if>
                <if test="fetchFields!=null">
                    <if test="fetchFields.AllFields !=null">
                        *,
                    </if>
                    <if test="fetchFields.AllFields ==null and fetchFields.fetchFields==null and fetchFields.excludeFields==null and fetchFields.otherFields==null">
                        *,
                    </if>
                    <if test="fetchFields.AllFields==null and fetchFields.fetchFields!=null">
                <#list attrs as attr>
                    <if test="fetchFields.fetchFields.${attr.columnName}==true">
                        ${attr.columnName},
                    </if>
                </#list>
                    </if>
                    <if test="fetchFields.AllFields==null and fetchFields.excludeFields!=null">
                <#list attrs as attr>
                    <if test="fetchFields.excludeFields.${attr.columnName}==null">
                        ${attr.columnName},
                    </if>
                </#list>
                    </if>
                    <if test="fetchFields.otherFields!=null and fetchFields.otherFields.size>0">
                        <foreach collection="fetchFields.otherFields" index="index" item="item" separator=",">
                        ${r"#{item}"}
                        </foreach>
                    </if>
                </if>
            </if>
            <if test="(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == false" >
                *,
            </if>

        </trim>
    </sql>
