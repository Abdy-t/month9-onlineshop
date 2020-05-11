<#--
 объявляем макро-функцию используя которую мы создадим шаблон страницы
 https://freemarker.apache.org/docs/dgui_misc_userdefdir.html
 -->
<#macro renderWith title="Welcome to EduFood! Your one stop shop to purchase food!" scripts=[] styles=[]>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title>${title}</title>

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
                    <img src="images/label.png" alt="Shop">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-nav" aria-controls="main-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="main-nav">
                    <ul class="navbar-nav text-uppercase pt-2">
                        <li class="nav-item">
                            <a class="nav-link" href="/brands">BRANDS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/register">REGISTRATION</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/profile">PROFILE</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/login">LOGIN</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/search">SEARCH</a>
                        </li>
                    </ul>
                    <button type="button" class="button-sign btn btn-secondary" data-toggle="modal" data-target="#modal-form-autorization">sign up</button>
                    <form class="" action="/logout" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <button class="button-sign btn btn-secondary">LOGOUT </button>
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
                    <h5 class="modal-title" id="form-title">Autorization</h5>
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
                            <label for="exampleInputEmail1" class="flex flex-v-center "> Email address </label>
                            <input type="email" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required  value="${(dto.email)!''}" autofocus >
                        </div>
                        <div>
                            <label class="flex flex-v-center label-input-text" for="exampleInputPassword1"></label>
                            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password" value="" required>
                        </div>
                        <button type="submit" class="send btn btn-secondary">Send</button>
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