use `online_shop`;

CREATE TABLE `reviews` (
                           `id` int auto_increment NOT NULL,
                           `customer_id` int not null,
                           `product_id` int not null,
                           `date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `comment` varchar(128),
                           PRIMARY KEY (`id`),
                           CONSTRAINT `frk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
                           CONSTRAINT `frk_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
