    <!DOCTYPE html>
    <html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson 65. JPA & ORM</title>
</head>
<body>
    <form action="/register" method="post">
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Адрес почты:</p>
                <input type="text" name="email" placeholder="ваш адрес почты"  value="${(form.email)!''}" >
            </label>
        </div>
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Имя:</p>
                <input type="text" name="name" placeholder="ваше имя"  value="${(form.name)!''}">
            </label>
        </div>
        <div>
            <button type="submit">Регистрация</button>
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
</body>
</html>