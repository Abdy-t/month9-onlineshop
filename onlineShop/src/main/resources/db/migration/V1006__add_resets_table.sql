use `online_shop`;

CREATE TABLE `resets` (
                        `id` int auto_increment NOT NULL,
                        `token` varchar(128),
                        `customer_id` int not null,
                        PRIMARY KEY (`id`),
                        CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
                       ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
