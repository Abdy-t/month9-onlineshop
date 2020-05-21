<#import "/spring.ftl" as spring>
<div>
    <#if hasPrev>
        <a id="loadPrev" href="${prevPageLink!''}">&lt;&lt;&lt;<@spring.message "previous"/></a>
    <#else>
        <span id="loadPrev"><@spring.message "previous"/></span>
    </#if>

    <#if hasNext>
        <a id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!4}"><@spring.message "next"/>&gt;&gt;&gt;</a>
    <#else>
        <span><@spring.message "next"/></span>
    </#if>
</div>
