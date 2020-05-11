'use strict';

async function searchProduct(form){
    let data = new FormData(form);
    let product_name = data.get("name");


    let url = 'http://localhost:8080/api/products/search/' + product_name;
    let productsData = await fetch(url).then(p => p.json());

    let productList = document.getElementById("itemList");

    productList.innerHTML = createProductElements(productsData).innerHTML;
}

async function searchProductByPrice(form){
    let data = new FormData(form);
    let product_min = data.get("min");
    let product_max = data.get("max");

    let url = 'http://localhost:8080/api/products/search/' + product_min + "/" + product_max;
    let productsData = await fetch(url).then(p => p.json());

    let productList = document.getElementById("itemList");

    productList.innerHTML = createProductElements(productsData).innerHTML;
}

function createProductElements(products) {
    for(let i=0; i<products.length; i++){
        let product = new Product(products[i].id, products[i].image, products[i].name, products[i].price);
        let elem = createProductElement(product);
        document.getElementById("itemList").appendChild(elem);
    }
}

function createProductElement(product){
    let product_content = `<img src="/images/productImage/${product.image}" class="card-img-top" alt="${product.id}" width="100" height="111"> 
                           <a href="/product/${product.id}"> ${product.name} </a>
                            <p>Name: ${product.name}</p>
                            <p>Price: ${product.price}</p>`;
    let product_el = document.createElement(`div`);
    product_el.innerHTML += product_content;
    product_el.classList.add("flex", "flex-column", "box", "flex-v-center");
    return product_el;
}

class Product {
    constructor(id, image, name, price) {
        this.id = id;
        this.image=image;
        this.name = name;
        this.price = price;
    }
}
