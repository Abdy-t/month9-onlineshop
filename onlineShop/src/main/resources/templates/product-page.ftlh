<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/product.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["/css/bootstrap.min.css", "/css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    <div class="card mb-3 flex flex-column box flex-v-center" style="text-align:center">
        <img src="/images/productImage/${product.image}" class="item-icon" alt="${product.id}">
        <h3>Name: ${product.name}</h3>
        <p>Price: ${product.price}</p>
        <p>Quantity: ${product.qty}</p>
        <p>Brand: ${product.brand.name}</p>
        <p>Category: ${product.category.name}</p>
    </div>
</@main.renderWith>