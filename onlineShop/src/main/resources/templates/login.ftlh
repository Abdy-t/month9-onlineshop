<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <h2>Please sign in</h2>
    <form action="/login" method="post">

        <div>
            <#if error!false >
                <p style="color:red">Неверные имя пользователя или пароль !</p>
            </#if>
        </div>

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
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
        <div>
            <button type="submit" class="send btn btn-secondary">Login</button>
        </div>
    </form>
    <div class="bottom-box">
        <a href="/">На главную страницу</a>
    </div>
</@main.renderWith>
