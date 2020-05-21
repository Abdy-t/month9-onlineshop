<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]
scripts=["js/product.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js", "js/search.js"]>

    <h1 style="text-align:center"><@spring.message "welcome" /> <b><@spring.message "store.name" /></b></h1>

    <footer class="">
        <div class="footer container"><@spring.message "footer" />
        </div>
    </footer>
</@main.renderWith>
