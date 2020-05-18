use `online_shop`;

CREATE TABLE `orders` (
                        `id` INT auto_increment NOT NULL,
                        `customer_id` int not null,
                        `product_id` int not null,
                        `date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `qty` int not null,
                        `price` float not null,
                        PRIMARY KEY (`id`),
                        CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
                        CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
                       ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
