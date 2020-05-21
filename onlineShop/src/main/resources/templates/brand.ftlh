<#import "partials/main.ftlh" as main />
<#import "/spring.ftl" as spring>
<@main.renderWith scripts=["/js/main.js", "js/jquery-3.3.1.slim.min.js", "js/popper.min.js", "js/bootstrap.min.js", "https://kit.fontawesome.com/7174afae6d.js"]
styles=["css/bootstrap.min.css", "css/style.css", "https://fonts.googleapis.com/css?family=Raleway:400,600,700,800,900&display=swap"]>
    </br>
    <h3 style="text-align:center"><@spring.message "brands"/></h3>
    </br>
    <div class="flex flex-wrap" id="itemList">
        <#list items as listItem>
            <div class="flex flex-column box flex-v-center">
                <a href="/brand/${listItem.id}" class="btn" style="background-color: #cdfff5">
                    <div class="flex flex-column flex-v-center box-128">
                        <img class="item-icon" src="images/${listItem.icon}" alt="${listItem.name}">
                        ${listItem.name}
                    </div>
                </a>
            </div>
        </#list>
    </div>
    <#include "partials/pagination.ftlh">
</@main.renderWith>
