'use strict';

const serverPath = 'http://localhost:8080/api';

const getCurrentPage = () => {
    const loc = (typeof window.location !== 'string') ? window.location.search : window.location;
    const index = loc.indexOf('page=');
    return index === -1 ? 1 : parseInt(loc[index + 5]) + 1;
};

const constructGetUrl = (url, queryParams) => {
    for (let key in queryParams) {
        if (queryParams.hasOwnProperty(key)) {
            console.log(key, queryParams[key]);
        }
    }
    // TODO
};

(function loadPlacesPageable() {

    const placeTemplate = (listItem) => {
        const template = `<div class="flex flex-column box flex-v-center">
                             <a href="/product/${listItem.id}" class="btn" style="background-color: #ffd401">
                                <div class="flex flex-column flex-v-center box-128">
                                    <img src="/images/productImage/${listItem.image}" class="item-icon card-img-top" alt="${listItem.id}" width="100" height="111">
                                </div>
                             </a>
                             <p>Name: ${listItem.name}</p>
                              <p>Price: ${listItem.price}</p>
                          </div> 
        `;

        const elem = document.createElement('div');
        elem.innerHTML = template.trim();

        // return inner div with classes flex etc
        return elem.children[0];
    };

    const fetchPlaces = async (page, size) => {
        const placesPath = `${serverPath}/products?page=${page}&size=${size}`;
        const data = await fetch(placesPath, {cache: 'no-cache'});
        return data.json();
    };

    const loadNextPlacesGenerator = (loadNextElement, page) => {
        return async (event) => {
            event.preventDefault();
            event.stopPropagation();

            const defaultPageSize = loadNextElement.getAttribute('data-default-page-size');
            const data = await fetchPlaces(page, defaultPageSize);

            loadNextElement.hidden = data.length === 0;
            if (data.length === 0) {
                return;
            }

            const list = document.getElementById('itemList');
            for (let item of data) {
                list.append(placeTemplate(item));
            }

            loadNextElement.addEventListener('click', loadNextPlacesGenerator(loadNextElement, page + 1), {once: true});
            window.scrollTo(0, document.body.scrollHeight);
        };
    };
    document.getElementById('loadPrev').hidden = true;
    const loadNextElement = document.getElementById('loadNext');
    if (loadNextElement !== null) {
        loadNextElement.innerText = "Load more";
        loadNextElement.addEventListener('click', loadNextPlacesGenerator(loadNextElement, getCurrentPage()), {once: true});
    }

})();


