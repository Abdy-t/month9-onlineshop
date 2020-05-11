use `online_shop`;

CREATE TABLE `brands` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `icon` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `categories` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `image` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `products` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `image` varchar(128) NOT NULL,
    `price` float not null,
    `brand_id` int not null,
    `category_id` int not null,
    `qty` int not null,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_brand` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
    CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
);
