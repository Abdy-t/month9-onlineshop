<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Search page</title>
        <script src="/js/search.js"></script>
    </head>
    <body>
        <h1>Содержимое страницы</h1>
        <#if products??>
            <#list products as product>
                <div class="flex flex-column box flex-v-center">
                    <img class="food-icon" src="images/${product.image}" alt="${product.name}">
                    <p>${product.name}</p>
                    <p>${product.price}</p>
                    <p>${product.brand}</p>
                    <p>${product.category}</p>
                    <p>${product.qty}</p>
                </div>
            </#list>
        <#else>
            <h1>Products not found!</h1>
        </#if>
    </body>
</html>
