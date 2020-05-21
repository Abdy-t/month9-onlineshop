<#--<#import "partials/main.ftlh" as main />-->
<#--<#import "/spring.ftl" as spring>-->
<#--<@main.renderWith styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]-->
<#--scripts=["js/product.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js", "js/search.js"]>-->
<#--        <h1><label<@spring.message "search.product"/></label></h1>-->
<#--    <label><@spring.message "search.product.name"/></label>-->
<#--    <form id="search-form" name="search-form" enctype="multipart/form-data">-->
<#--        <input type="text" placeholder="<@spring.message "search.product.name"/>" name="name" class="input-search"/>-->
<#--        <button type="button" class="button-search btn btn-secondary" id="search-button" onclick="searchProduct(this.form)"><@spring.message "search"/></button>-->
<#--    </form>-->
<#--    <label><@spring.message "search.product.price"/></label>-->
<#--    <form id="search-form" action="/cart/add" method="post"name="search-form" enctype="multipart/form-data">-->
<#--        <input type="number" placeholder="<@spring.message "price.min"/>" name="min" class="input-search"/>-->
<#--        <input type="number" placeholder="<@spring.message "price.max"/>" name="max" class="input-search"/>-->
<#--        <button type="submit" class="button-search btn btn-secondary" id="search-button"><@spring.message "search"/></button>-->
<#--    </form>-->
<#--    <div class="flex flex-wrap" id="itemList">-->
<#--         <#list items as listItem>-->
<#--             <p>Name: ${listItem.name}</p>-->
<#--             <p>Price: ${listItem.price}</p>-->
<#--         </#list>-->
<#--    </div>-->
<#--    <div>-->
<#--        <a href="/"><@spring.message "main.page"/></a>-->
<#--    </div>-->
<#--</@main.renderWith>-->
<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]
scripts=["js/product.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js", "js/search.js"]>
    <h1><label<@spring.message "search.product"/></label></h1>
    <label><@spring.message "search.product.name"/></label>
    <form id="search-form" name="search-form" enctype="multipart/form-data">
        <input type="text" placeholder="<@spring.message "search.product.name"/>" name="name" class="input-search"/>
        <button type="button" class="button-search btn btn-secondary" id="search-button" onclick="searchProduct(this.form)"><@spring.message "search"/></button>
    </form>
    <label><@spring.message "search.product.price"/></label>
    <form id="search-form" name="search-form" enctype="multipart/form-data">
        <input type="number" placeholder="<@spring.message "price.min"/>" name="min" class="input-search"/>
        <input type="number" placeholder="<@spring.message "price.max"/>" name="max" class="input-search"/>
        <button type="button" class="button-search btn btn-secondary" id="search-button" onclick="searchProductByPrice(this.form)"><@spring.message "search"/></button>
    </form>
    <div class="flex flex-wrap" id="itemList">

    </div>
    <div>
        <a href="/"><@spring.message "main.page"/></a>
    </div>
</@main.renderWith>
