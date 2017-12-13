<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageMapper}.write.${mapperName}WriteMapper">

    <!--生成代码开始 don't delete-->


    <insert id="insert${className}">
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

    <update id="update${className}">
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
    <!--生成代码结束  don't delete-->

</mapper>