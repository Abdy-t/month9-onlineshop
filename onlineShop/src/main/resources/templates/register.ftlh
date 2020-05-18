<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <h2>Please register</h2>
    <div>
        <form action="/register" method="post">

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="form-group">
                <label class="flex flex-v-center" for="exampleInputName1">
                    <p class="label-input-text">Name:</p>
                    <input type="text" name="name" class="form-control" placeholder="Your name"  value="${(dto.name)!''}">
                </label>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1" class="flex flex-v-center">
                    <p class="label-input-text">Email address:</p>
                    <input type="email" name="email" class="form-control" placeholder="Email address" aria-describedby="emailHelp" required value="${(dto.email)!''}" >
                </label>
            </div>
            <div class="form-group">
                <label class="flex flex-v-center label-input-text" for="exampleInputPassword1">
                    <p class="label-input-text">Password:</p>
                    <input type="password" name="password" class="form-control" placeholder="Password"  value="" required>
                </label>
            </div>
            <div class="g-recaptcha" data-sitekey="6LfeMvcUAAAAAI3vO7wjoBgm3sksNNv38pREfCeA"></div>
            <div>
            <div>
                <button type="submit" class="send btn btn-secondary">Registration</button>
            </div>
            <div>
                <#if errors?? >
                    <p>Входные данные с ошибками!</p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
            </div>
        </form>
    </div>
    </br>
    <div class="bottom-box">
        <a href="/">MAIN PAGE</a>
    </div>
</@main.renderWith>