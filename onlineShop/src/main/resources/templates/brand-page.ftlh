<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith scripts=["js/product.js", "/js/jquery-3.3.1.slim.min.js", "/js/popper.min.js", "/js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["/css/bootstrap.min.css", "/css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <div class="card mb-3" style="text-align:center">
        <img src="/images/${brand.icon}" class="item-icon" style="margin: auto " alt="${brand.id}">
        <h2 style="text-align:center">${brand.name}</h2>
    </div>
    <div class="flex flex-wrap" id="itemList">
        <#list items as listItem>
            <div class="flex flex-column box flex-v-center">
                <a href="/product/${listItem.id}" class="btn" style="background-color: #ffd401">
                    <div class="flex flex-column flex-v-center box-128">
                        <img src="/images/productImage/${listItem.image}" class="item-icon" alt="${listItem.id}" width="100" height="111">
                    </div>
                </a>
                <p><@spring.message "name"/>: ${listItem.name}</p>
                <p><@spring.message "price"/>: ${listItem.price}</p>
            </div>
        </#list>
    </div>
    <#include "partials/pagination.ftlh">
</@main.renderWith>