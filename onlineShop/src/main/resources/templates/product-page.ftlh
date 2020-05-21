<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith scripts=["/product.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["/css/bootstrap.min.css", "/css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <div class="card mb-3 flex flex-column box flex-v-center" style="text-align:center">
        <img src="/images/productImage/${product.image}" class="item-icon" alt="${product.id}">
        <h3><@spring.message "name"/>: ${product.name}</h3>
        <p><@spring.message "price"/>: ${product.price}</p>
        <p><@spring.message "quantity"/>: ${product.qty}</p>
        <p><@spring.message "brand"/>: ${product.brand.name}</p>
        <p><@spring.message "category"/>: ${product.category.name}</p>
        </br>
        <label>-- <@spring.message "title.comment"/> -- </label>
        <#list product.review as listItem>
            <div class="flex flex-column box flex-v-center">
                    <div class="flex flex-column flex-v-center box-128">
                        <p style="color:blueviolet">${listItem.userEmail}</p>
                        <p style="margin-right: auto">${listItem.comment}</p>
                    </div>
            </div>
        </#list>
    </div>
    <form action="/cart/add" method="post" id="cart-form">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div>
            <label for="quantity"><@spring.message "quantity"/> (<@spring.message "between"/>):</label>
            <input type="number" id="quantity" name="quantity" value="1" min="1" max="10">
            <button type="submit" name="value" value="${product.id}"><@spring.message "cart.add"/></button>
        </div>
        <div>
            <a href="/cart" class="value-cell"><@spring.message "cart.show"/></a>
        </div>
    </form>
</@main.renderWith>