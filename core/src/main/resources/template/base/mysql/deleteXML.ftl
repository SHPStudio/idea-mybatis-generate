    <delete id="delete${className}">
        DELETE FROM ${sense}${tableName}${sense}
        WHERE
        <trim prefix="where" suffixOverrides="and | or">
            <#list attrs as attr>
                <if test="where.${attr.propertiesName}List != null">
                    ${sense}${attr.columnName}${sense} in
                    <foreach collection="where.${attr.propertiesName}List" close=")" open="(" separator="," item="item">
                        ${"#\{"}item}
                    </foreach> and
                </if>
            <#if attr.isBetween = "yes">
            <if test="where.${attr.propertiesName}St !=null">
                ${sense}${attr.columnName}${sense} >= ${"#\{where."}${attr.propertiesName}St} and
            </if>
            <if test="where.${attr.propertiesName}Ed!=null">
                ${sense}${attr.columnName}${sense} &lt;= ${"#\{where."}${attr.propertiesName}Ed} and
            </if>
            </#if>
            <#if attr.javaTypeName = "String">
            <if test ="where.fuzzy${attr.propertiesName?cap_first}!=null and where.fuzzy${attr.propertiesName?cap_first}.size()>0">
                (
                <foreach collection="where.fuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                    ${sense}${attr.columnName?cap_first}${sense} like concat('%',${"#\{"}item},'%')
                </foreach>
                ) and
            </if>
            <if test ="where.rightFuzzy${attr.propertiesName?cap_first}!=null and where.rightFuzzy${attr.propertiesName?cap_first}.size()>0">
                (
                <foreach collection="where.rightFuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                    ${sense}${attr.columnName?cap_first}${sense} like concat(${"#\{"}item},'%')
                </foreach>
                ) and
            </if>
            </#if>
            </#list>
        </trim>
    </delete>