<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Search page</title>
        <script src="/js/search.js"></script>
    </head>
    <body>
        <h1>Содержимое страницы</h1>
        <form id="search-form" name="search-form" enctype="multipart/form-data">
            <input type="text" size="24" placeholder="name" name="name"/> </br>
            <input type="number" size="7" placeholder="min price" name="minPrice"/> </br>
            <input type="number" size="7" placeholder="max price" name="maxPrice"/> </br>
            <input type="text" size="24" placeholder="brand name" name="brand"/> </br>
            <input type="text" size="24" placeholder="category name" name="category"/> </br>
            <button type="submit" class='change' id="search-button" >Search</button>
        </form>
        <#if products??>
            <#list products as product>
                <div class="flex flex-column box flex-v-center">
                    <img class="food-icon" src="/images/${product.image}" alt="${product.name}">
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
