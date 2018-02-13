<#if databaseType = "mysql">
    <#include "mysql/selectXML.ftl">
</#if>
<#if databaseType = "postgre">
    <#include "postgre/selectXML.ftl">
</#if>
