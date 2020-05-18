<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=["/js/main.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "css/main.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    </br>
    <div class="container">
        <h1>Your orders</h1>
        <div class="flex flex-wrap" id="itemList">
        <#if orderItems??>
            <#list orderItems as item>
                 <div class="flex flex-column box flex-v-center">
                     <a href="/product/${item.id}" class="btn" style="background-color: #ffd401">
                         <div class="flex flex-column flex-v-center box-128">
                             <img src="/images/productImage/${item.image}" class="item-icon" alt="${item.id}" width="100" height="111">
                         </div>
                     </a>
                    <p>Name: ${item.name}</p>
                    <p>Price: ${item.price}</p>
                    <p>Brand: ${item.brand.name}</p>
                    <p>Category: ${item.category.name}</p>
                    <p>Quantity: ${item.qty}</p>
                 </div>
            </#list>
            </br>
        <#else>
            <p>There is nothing here!</p>
        </#if>
        </br>
        </div>
        <div>
            <a href="/">BACK</a>
        </div>
    </div>
</@main.renderWith>
