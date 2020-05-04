use `online_shop`;

CREATE TABLE `customers` (
     `id` int auto_increment NOT NULL,
     `email` varchar(128) NOT NULL,
     `password` varchar(128) NOT NULL,
     `fullname` varchar(128) NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `email_unique` (`email` ASC)
);


