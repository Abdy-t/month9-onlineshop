<#import "partials/main.ftlh" as main />
<@main.renderWith styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]
scripts=["js/product.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js", "js/search.js"]>
        <h1>Search products</h1>
    <label>Search by name product</label>
    <form id="search-form" name="search-form" enctype="multipart/form-data">
        <input type="text" placeholder="product name" name="name" class="input-search"/>
        <button type="button" class="button-search btn btn-secondary" id="search-button" onclick="searchProduct(this.form)">Search</button>
    </form>
    <label>Search by price product</label>
    <form id="search-form" name="search-form" enctype="multipart/form-data">
        <input type="number" placeholder="Min price" name="min" class="input-search"/>
        <input type="number" placeholder="Max price" name="max" class="input-search"/>
        <button type="button" class="button-search btn btn-secondary" id="search-button" onclick="searchProductByPrice(this.form)">Search</button>
    </form>
    <div class="flex flex-wrap" id="itemList">

    </div>
    <div>
        <a href="/">Back to home page</a>
    </div>
</@main.renderWith>

