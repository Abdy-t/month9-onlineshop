'use strict';

window.addEventListener('load', function () {
    const searchButton = document.getElementById("search-button");
    searchButton.addEventListener("click",function () {
        const searchForm = document.getElementById("search-form");
        let data = new FormData(searchForm);
        fetch("http://localhost:8080/search",{
            method: 'POST',
            body: data
        }).then(r => r.json()).then(data => {
            window.location.href = "http://localhost:8080/search/result";
            console.log(data)
        });
    });
});
