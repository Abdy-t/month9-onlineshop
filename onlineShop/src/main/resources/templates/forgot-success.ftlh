<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=["/js/main.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "css/main.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <h2>Please enter token</h2>
    <#if errorText?? >
        <div>
            <p>${errorText!'-- no message --'}</p>
        </div>
    </#if>
    <div>
    <form action="/reset-password" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Token:</p>
                <input type="text" name="token" placeholder="token" >
            </label>
        </div>
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">New Password:</p>
                <input type="text" name="newPassword" placeholder="new password" >
            </label>
        </div>
        <div>
            <button type="submit">Отправить</button>
        </div>
    </form>
    </div>
    <div class="bottom-box">
        <a href="/">На главную страницу</a>
    </div>
</@main.renderWith>