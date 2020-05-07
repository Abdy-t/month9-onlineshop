use `online_shop`;

INSERT INTO categories (`name`, `image`) VALUES ('Phone', 'phone-logo.png') , ('TV', 'tv-logo.png');


INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Galaxy s10','galaxy-image.png', 600, (SELECT id FROM brands where name='samsung'),
                                                  (SELECT id FROM categories where name='Phone'), 10);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Iphone 11','iphone11-image.png', 1000, (SELECT id FROM brands where name='apple'),
                                                    (SELECT id FROM categories where name='Phone'), 5);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Iphone 8','iphone8-image.png', 500, (SELECT id FROM brands where name='apple'),
                                                 (SELECT id FROM categories where name='Phone'), 13);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Nokia 1110','nokia1110-image.png', 15, (SELECT id FROM brands where name='nokia'),
                                                 (SELECT id FROM categories where name='Phone'), 50);


