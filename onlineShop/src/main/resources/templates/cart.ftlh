<#import "partials/main.ftlh" as main />
<@main.renderWith scripts=["/js/main.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "css/main.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    </br>
    <div class="container">
        <h1>Your cart</h1>
        <#if cartItems??>
            <#list cartItems as item>
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
                </br>
                <form action="/cart/remove" method="post" id="cart-form">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div>
                        <label for="quantity">Quantity:</label>
                        <input type="number" id="quantity" name="quantity" value="1" min="1">
                        <button type="submit" name="value" value="${item.id}">Remove</button>
                    </div>
                </form>
                <form action="/cart/add" method="post" id="cart-form">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div>
                        <label for="quantity">Quantity (between 1 and 10):</label>
                        <input type="number" id="quantity" name="quantity" value="1" min="1" max="10">
                        <button type="submit" name="value" value="${item.id}">Add to cart</button>
                    </div>
                </form>
                <form action="/order/buy" method="post" id="cart-form">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div>
                        <label for="quantity">Quantity:</label>
                        <input type="number" id="quantity" name="quantity" value="1" min="1">
                        <button type="submit" name="value" value="${item.id}">Buy</button>
                    </div>
                </form>
            </#list>
            </br>
            <form action="/cart/empty" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button>Clear cart</button>
            </form>
        <#else>
            <p>There is nothing here!</p>
        </#if>
        </br>
        <div>
            <a href="/">Back</a>
        </div>
    </div>
</@main.renderWith>
