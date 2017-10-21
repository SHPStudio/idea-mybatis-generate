<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageMapper}.${className}Mapper">

    <!--生成代码开始 don't delete-->


    <insert id="insert">
        INSERT INTO ${tableName}
        (
       <#list attrs as attr>
            <#if attr.isAuto == "NO">
                <#if attr_has_next>
        ${attr.columnName},
                </#if>
                <#if !attr_has_next>
        ${attr.columnName}
                </#if>
            </#if>
       </#list>
        )
        VALUES
        (
        <#list attrs as attr>
            <#if attr.isAuto == "NO">
                <#if attr_has_next>
        ${"#\{"}${attr.columnName}},
                </#if>
                <#if !attr_has_next>
        ${"#\{"}${attr.columnName}}
                </#if>
            </#if>
        </#list>
        );
    </insert>

    <update id="update">
    UPDATE ${tableName}
    SET
    <trim suffixOverrides=",">
    <#list attrs as attr>
        <#if attr.isAuto == "NO" && attr.isKey == 0>
        <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
        ${attr.columnName} = ${"#\{"}${attr.columnName}},
        </if>
        </#if>
    </#list>
    </trim>
    WHERE
    <trim suffixOverrides="and">
    <#list attrs as attr>
        <#if attr.isKey == 1>
        ${attr.columnName} = ${"#\{"}${attr.columnName}} and
        </#if>
    </#list>
    </trim>
    </update>
    <select id="query" resultType="${packageModel}.${className}">
        select
        <include refid="baseResult"></include>
        from  ${tableName}
        <trim prefix="where" suffixOverrides="and | or">
            <#list attrs as attr>
            <if test="${attr.columnName} != null<#if attr.javaTypeName=="String"> and ${attr.columnName}!=''</#if>">
            ${attr.columnName} = ${"#\{"}${attr.columnName}} and
            </if>
            </#list>
        </trim>
    </select>

    <#list attrs as attr>
        <#if attr.isKey == 1>
    <select id="getBy${attr.columnName?cap_first}" resultType="${packageModel}.${className}">
        select
            *
        from ${tableName}
        where
        ${attr.columnName} = ${"#\{"}${attr.columnName}}
    </select>
        </#if>
    </#list>


    <sql id="baseResult">
        <trim suffixOverrides=",">
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
        </trim>
    </sql>
    <!--生成代码结束  don't delete-->

</mapper>