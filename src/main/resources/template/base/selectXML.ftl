
    <select id="query${className}" resultType="${packageModel}.${className}">
        select
        <include refid="baseResult"></include>
        from  ${sense}${tableName}${sense}
        <trim prefix="where" suffixOverrides="and | or">
                <#list attrs as attr>
            <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.columnName}} and
            </if>
                </#list>
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">
                <#list attrs as attr>
                <if test="${attr.columnName}List != null">
                    ${sense}${attr.columnName}${sense} in
                    <foreach collection="${attr.columnName}List" close=")" open="(" separator="," item="item">
                        ${"#\{"}item}
                    </foreach> and
                </if>
                <#if attr.isBetween = "yes">
                <if test="${attr.columnName}St !=null">
                    ${sense}${attr.columnName}${sense} >= ${"#\{"}${attr.columnName}St} and
                </if>
                <if test="${attr.columnName}Ed!=null">
                    ${sense}${attr.columnName}${sense} &lt;= ${"#\{"}${attr.columnName}Ed} and
                </if>
                </#if>
                <#if attr.javaTypeName = "String">
                <if test ="fuzzy${attr.columnName?cap_first}!=null and fuzzy${attr.columnName?cap_first}.size()>0">
                    (
                    <foreach collection="fuzzy${attr.columnName?cap_first}"  separator="or" item="item">
                        ${sense}${attr.columnName?cap_first}${sense} like concat('%',${"#\{"}item},'%')
                    </foreach>
                    ) and
                </if>
                <if test ="rightFuzzy${attr.columnName?cap_first}!=null and rightFuzzy${attr.columnName?cap_first}.size()>0">
                    (
                    <foreach collection="rightFuzzy${attr.columnName?cap_first}"  separator="or" item="item">
                        ${sense}${attr.columnName?cap_first}${sense} like concat(${"#\{"}item},'%')
                    </foreach>
                    ) and
                </if>
                </#if>
                </#list>
            </if>
        </trim>
    </select>

    <select id="query${className}Limit1" resultType="${packageModel}.${className}">
        select
        <include refid="baseResult"></include>
        from  ${sense}${tableName}${sense}
        <trim prefix="where" suffixOverrides="and | or">
            <#list attrs as attr>
            <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.columnName}} and
            </if>
            </#list>
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">
            <#list attrs as attr>
                <if test="${attr.columnName}List != null">
                    ${sense}${attr.columnName}${sense} in
                    <foreach collection="${attr.columnName}List" close=")" open="(" separator="," item="item">
                        ${"#\{"}item}
                    </foreach> and
                </if>
                <#if attr.isBetween = "yes">
                <if test="${attr.columnName}St !=null">
                    ${sense}${attr.columnName}${sense} >= ${"#\{"}${attr.columnName}St and
                </if>
                <if test="${attr.columnName}Ed!=null">
                    ${sense}${attr.columnName}${sense} &lt;= ${"#\{"}${attr.columnName}Ed} and
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
                        ${sense}${attr.columnName}${sense},
                    </if>
                </#list>
                    </if>
                    <if test="fetchFields.AllFields==null and fetchFields.excludeFields!=null">
                <#list attrs as attr>
                    <if test="fetchFields.excludeFields.${attr.columnName}==null">
                        ${sense}${attr.columnName}${sense},
                    </if>
                </#list>
                    </if>
                    <if test="fetchFields.otherFields!=null and fetchFields.otherFields.size>0">
                        <foreach collection="fetchFields.otherFields" index="index" item="item" separator=",">
                        ${sense}${r"#{item}"}${sense}
                        </foreach>
                    </if>
                </if>
            </if>
            <if test="(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == false" >
                *,
            </if>

        </trim>
    </sql>
