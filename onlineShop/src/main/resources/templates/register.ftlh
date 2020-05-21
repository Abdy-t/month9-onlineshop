<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith scripts=["js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <h2><@spring.message "title.register"/></h2>
    <div>
        <form action="/register" method="post">

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="form-group">
                <label class="flex flex-v-center" for="exampleInputName1">
                    <p class="label-input-text"><@spring.message "user.name"/>:</p>
                    <input type="text" name="name" class="form-control" placeholder="<@spring.message "enter.user.name" />"  value="${(dto.name)!''}">
                </label>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1" class="flex flex-v-center">
                    <p class="label-input-text"><@spring.message "email" />:</p>
                    <input type="email" name="email" class="form-control" placeholder="<@spring.message "enter" />" aria-describedby="emailHelp" required value="${(dto.email)!''}" >
                </label>
            </div>
            <div class="form-group">
                <label class="flex flex-v-center label-input-text" for="exampleInputPassword1">
                    <p class="label-input-text"><@spring.message "password" />:</p>
                    <input type="password" name="password" class="form-control" placeholder="<@spring.message "enter.password" />"  value="" required>
                </label>
            </div>
            <div class="g-recaptcha" data-sitekey="6LfeMvcUAAAAAI3vO7wjoBgm3sksNNv38pREfCeA"></div>
            <div>
            <div>
                <button type="submit" class="send btn btn-secondary"><@spring.message "registration" /></button>
            </div>
            <div>
                <#if errors?? >
                    <p><@spring.message "error.input"/></p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
            </div>
        </form>
    </div>
    </br>
    <div class="bottom-box">
        <a href="/"><@spring.message "main.page" /></a>
    </div>
</@main.renderWith>