use `online_shop`;

INSERT INTO categories (`name`, `image`) VALUES ('Phone', 'phone-logo.png') , ('TV', 'tv-logo.png');


INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Galaxy S10','galaxy-image.png', 600, (SELECT id FROM brands where name='SAMSUNG'),
         (SELECT id FROM categories where name='Phone'), 10);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Galaxy A7','galaxya7-image.png', 300, (SELECT id FROM brands where name='SAMSUNG'),
        (SELECT id FROM categories where name='Phone'), 35);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Galaxy Note10+','galaxynote10-image.png', 1100, (SELECT id FROM brands where name='SAMSUNG'),
        (SELECT id FROM categories where name='Phone'), 22);

INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Iphone 11','iphone11-image.png', 1000, (SELECT id FROM brands where name='APPLE'),
         (SELECT id FROM categories where name='Phone'), 5);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Iphone 8','iphone8-image.png', 500, (SELECT id FROM brands where name='APPLE'),
           (SELECT id FROM categories where name='Phone'), 13);

INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Nokia 1110','nokia1110-image.png', 15, (SELECT id FROM brands where name='NOKIA'),
            (SELECT id FROM categories where name='Phone'), 50);

INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Redmi 7A','redmi7a-image.png', 114, (SELECT id FROM brands where name='XIAOMI'),
        (SELECT id FROM categories where name='Phone'), 43);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Redmi 8A','redmi7a-image.png', 140, (SELECT id FROM brands where name='XIAOMI'),
        (SELECT id FROM categories where name='Phone'), 57);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Redmi Note 8T','note8t-image.png', 540, (SELECT id FROM brands where name='XIAOMI'),
        (SELECT id FROM categories where name='Phone'), 25);
INSERT INTO products (`name`, `image`, `price`, `brand_id`, `category_id`, `qty`)
    VALUES ('Redmi Note 8 Pro','note8pro-image.png', 340, (SELECT id FROM brands where name='XIAOMI'),
        (SELECT id FROM categories where name='Phone'), 41);


