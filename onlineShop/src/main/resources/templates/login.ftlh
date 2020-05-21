<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith scripts=["js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <h2><@spring.message "title.login"/></h2>
    <form action="/login" method="post">

        <div>
            <#if error!false >
                <p style="color:red"><@spring.message "error.email.password"/></p>
            </#if>
        </div>

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="form-group">
            <label for="exampleInputEmail1" class="flex flex-v-center">
                <p class="label-input-text"><@spring.message "email"/>:</p>
                <input type="email" name="username" class="form-control" placeholder="<@spring.message "enter"/>" aria-describedby="emailHelp" required value="${(dto.email)!''}" >
            </label>
        </div>
        <div class="form-group">
            <label class="flex flex-v-center label-input-text" for="exampleInputPassword1">
                <p class="label-input-text"><@spring.message "password"/>:</p>
                <input type="password" name="password" class="form-control" placeholder="<@spring.message "enter.password"/>"  value="" required>
            </label>
        </div>
        <div>
            <button type="submit" class="send btn btn-secondary"><@spring.message "login"/></button>
        </div>
    </form>
    <div class="flex flex-justify-content-center">
        <a class="flex box-128" href="/forgot-password"><@spring.message "forgot.password"/></a>
    </div>
    <div class="bottom-box">
        <a href="/"><@spring.message "main.page"/></a>
    </div>
</@main.renderWith>
