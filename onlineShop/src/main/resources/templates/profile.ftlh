<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith scripts=["js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <#if dto??>
        <h1><@spring.message "personal.data"/></h1>
        <p>${(dto.fullname)!'-- missing --'} - ${(dto.email)!'-- email missing --'}</p>
    <#else>
        <h1><@spring.message "user.not.found"/></h1>
    </#if>
    <div class="bottom-box">
        <a href="/"><@spring.message "main.page"/></a>
    </div>
</@main.renderWith>