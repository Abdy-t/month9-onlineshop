use `online_shop`;

CREATE TABLE `carts` (
                        `id` INT auto_increment NOT NULL,
                        PRIMARY KEY (`id`)
                       ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;


ALTER TABLE `customers` ADD FOREIGN KEY(`cart`) REFERENCES `carts`(id);

CREATE TABLE `cart_story` (
                                    `id` INT auto_increment NOT NULL ,
                                    `cart_id` INTEGER NOT NULL,
                                    `product_id` INTEGER NOT NULL,
                                    `quantity` int,
                                    FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                    PRIMARY KEY (`id`)
);
