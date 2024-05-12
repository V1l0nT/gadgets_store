CREATE TABLE users
(
    id       serial PRIMARY KEY NOT NULL,
    username varchar(100),
    password varchar(64),
    role     varchar(50)
);

CREATE TABLE gadgets
(
    id          serial PRIMARY KEY NOT NULL,
    name        varchar(100),
    description varchar(300),
    price       decimal(10, 2),
    image       TEXT
);

CREATE TABLE orders
(
    id      serial PRIMARY KEY,
    total   int,
    date    DATE,
    user_id int REFERENCES users (id)
);

CREATE TABLE orders_gadgets
(
    order_id  int references orders (id),
    gadget_id int references gadgets (id)
);

CREATE TABLE users_gadgets
(
    user_id   int REFERENCES users (id),
    gadget_id int REFERENCES gadgets (id)
);

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$/1aicoVnRzAkuOM7Q9Pbt.OLJwbT67RAdEQRFnp9GBHkD6G3vVEz6', 'ROLE_ADMIN'),
       ('nikita', '$2a$10$/1aicoVnRzAkuOM7Q9Pbt.OLJwbT67RAdEQRFnp9GBHkD6G3vVEz6', 'ROLE_USER');

INSERT INTO gadgets (name, description, price, image)
VALUES ('Смартфон Galaxy S22 Ultra',
        'Новейший флагманский смартфон от Samsung с первоклассной камерой, производительностью и дисплеем.',
        40000.99, 'https://www.sammobile.com/wp-content/uploads/2022/01/s22-renders.png'),
       ('Ноутбук MacBook Pro M2',
        'Мощный ноутбук от Apple с новейшим чипом M2, потрясающим дисплеем Retina и временем автономной работы в течение всего дня.',
        155000.99, 'https://cdn.mos.cms.futurecdn.net/rv4qoZkAppaW3kVNfTDZAR.jpg'),
       ('Часы Apple Watch Series 8',
        'Изящные и стильные умные часы от Apple с расширенными функциями отслеживания состояния здоровья и плавной интеграцией с устройствами iOS.',
        30890.99, 'https://img.xcomdb.ru/pim/JPG/c9/8I20ed6HTQ_500.webp'),
       ('Дрон DJI Mavic 3',
        'Высокопроизводительный беспилотник с камерой Hasselblad, возможностью обхода препятствий и интеллектуальными режимами полета.',
        228890.99, 'https://cdn.vjshop.vn/tin-tuc/top-10-flycam-tot-nhat/top-10-flycam-dji-mavic-3-pro.jpg'),
       ('Беспроводные наушники Sony WH-1000XM5',
        'Высококачественные наушники с шумоподавлением от Sony с лучшим в отрасли качеством звука и комфортом.',
        40000.99, 'https://main-cdn.sbermegamarket.ru/big2/hlr-system/-74/082/264/419/234/7/100063793212b0.jpg');
