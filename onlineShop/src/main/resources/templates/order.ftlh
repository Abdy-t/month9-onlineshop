<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith scripts=["/js/main.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "css/main.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    </br>
    <div class="container">
        <h1><@spring.message "title.order"/></h1>
        <div class="flex flex-wrap" id="itemList">
        <#if orderItems??>
            <#list orderItems as item>
                 <div class="flex flex-column box flex-v-center">
                     <a href="/product/${item.id}" class="btn" style="background-color: #ffd401">
                         <div class="flex flex-column flex-v-center box-128">
                             <img src="/images/productImage/${item.image}" class="item-icon" alt="${item.id}" width="100" height="111">
                         </div>
                     </a>
                    <p><@spring.message "name"/>: ${item.name}</p>
                    <p><@spring.message "price"/>: ${item.price}</p>
                    <p><@spring.message "brand"/>: ${item.brand.name}</p>
                    <p><@spring.message "category"/>: ${item.category.name}</p>
                    <p><@spring.message "quantity"/>: ${item.qty}</p>
                 </div>
                <form action="/order/review" method="post" id="cart-form">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div>
                        <label for="quantity"><@spring.message "comment"/>:</label>
                        <input type="text" id="comment" name="comment">
                        <button type="submit" name="value" value="${item.id}"><@spring.message "send"/></button>
                    </div>
                </form>
            </#list>
            </br>
        <#else>
            <p><@spring.message "cart.info"/></p>
        </#if>
        </br>
        </div>
        <div>
            <a href="/"><@spring.message "main.page"/></a>
        </div>
    </div>
</@main.renderWith>
