<#--
 объявляем макро-функцию используя которую мы создадим шаблон страницы
 https://freemarker.apache.org/docs/dgui_misc_userdefdir.html
 -->
<#import "/spring.ftl" as spring>
<#macro renderWith title="Welcome to Electronic shop" scripts=[] styles=[] dir="ltr">
    <!doctype html>
    <html lang="${.locale}" dir="${dir}">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title>${title}</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <link rel="stylesheet" href="/css/main.css">
        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}">
        </#list>
    </head>
    <body>
    <header class="header">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark">
                <a class="navbar-brand mr-5" href="/">
                    <h5><@spring.message "shop"/></h5>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-nav" aria-controls="main-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="main-nav">
                    <form action="" method="get">
                        <label for="lang_select">
                            <@spring.message "label.language" />:
                        </label>
                        <select name="lang" id="lang_select">
                            <option value="en">English</option>
                            <option value="ru">Русский</option>
                        </select>
                        <button type="submit" class="button-sign btn btn-secondary">
                            <@spring.message "button.language-change.text" />
                        </button>
                    </form>
                    <ul class="navbar-nav text-uppercase pt-2">
                        <li class="nav-item">
                            <a class="nav-link" href="/brands"><@spring.message "brands" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/register"><@spring.message "registration" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/profile"><@spring.message "profile" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/login"><@spring.message "login" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/cart"><@spring.message "cart" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/order"><@spring.message "orders" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/search"><@spring.message "search" /></a>
                        </li>
                    </ul>
                    <button type="button" class="button-sign btn btn-secondary" data-toggle="modal" data-target="#modal-form-autorization"><@spring.message "sign.up" /></button>
                    <form class="" action="/logout" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <button class="button-sign btn btn-secondary"><@spring.message "logout" /> </button>
                    </form>
                </div>
                <#--        </div>-->
            </nav>
        </div>
    </header>
    <#--
    например если бы у нашего сайта было какое-то верхнее меню,
    то мы могли бы его тут указать
    и оно появится сразу на всех страницах которые используют
    этот шаблон
    -->

    <div class="container">
        <#--
         элемент nested указывает что в этом место
         будет подставлено некоторое содержимое,
         которое передаётся как тело макро-функции при её вызове
        -->
        <#nested>
    </div>
    <div class="modal fade" id="modal-form-autorization" tabindex="-1" role="dialog" aria-labelledby="form-title" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="form-title"><@spring.message "authorization"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
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
                            <label for="exampleInputEmail1" class="flex flex-v-center "><@spring.message "email"/></label>
                            <input type="email" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="<@spring.message "enter"/>" required  value="${(dto.email)!''}" autofocus >
                        </div>
                        <div>
                            <label class="flex flex-v-center label-input-text" for="exampleInputPassword1"><@spring.message "password"/></label>
                            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="<@spring.message "enter.password"/>" value="" required>
                        </div>
                        <button type="submit" class="send btn btn-secondary"><@spring.message "send"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>

    </body>
    </html>

</#macro>