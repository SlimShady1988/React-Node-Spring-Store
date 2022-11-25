-- INSERT INTO roles(name) VALUES ('USER');
-- INSERT INTO roles(name) VALUES ('ADMIN');
-- pass - aa
INSERT INTO users (email, password)
VALUES ('aa', '$2a$12$CQVERqfWtk7lrVw9AlEwEuhLoTEszE8LaWPxw8dAPpzODPrjrGw9W');
-- pass - bb
INSERT INTO users (email, password)
VALUES ('bb', '$2a$12$dHZObNXgflt8/b6zXqGTPu2PtbrhYUbyk8axKh44d3FbZZKBYoSre');
-- pass - cc
INSERT INTO users (email, password)
VALUES ('cc', '$2a$12$3IMgzAEi7iaZMZwBRPnw..OhNyt/KukaP1i8NFz5St6rJ5FKR8jT.');
-- pass - admin
INSERT INTO users (email, password, role)
VALUES ('admin', '$2a$12$px4/bgEOevvLvhSvM31gBOF1G0U3yBTDM4Fgz2nIindG7vIoBEB9G', 'ADMIN');


INSERT INTO types (name) VALUES ('Phone');
INSERT INTO types (name) VALUES ('Laptop');
INSERT INTO types (name) VALUES ('TV');
INSERT INTO types (name) VALUES ('Watch');
--
INSERT INTO brands (name) VALUES ('Samsung');
INSERT INTO brands (name) VALUES ('Apple');
INSERT INTO brands (name) VALUES ('HP');
INSERT INTO brands (name) VALUES ('Google');
INSERT INTO brands (name) VALUES ('Xiaomi');

--
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( '14 Pro Max', 1800, 5, '', 1, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( '13 Pro Max', 1550, 5, '', 1, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( '11 Pro Max', 1450, 5, '', 1, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( '10 Pro', 550, 5, '', 1, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Galaxy S20 Ultra', 800, 5, '', 1, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Galaxy S21', 850, 5, '', 1, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Galaxy S21 Ultra', 1050, 5, '', 1, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Galaxy S22 Ultra', 1400, 5, '', 1, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Pixel  5', 300, 5, '', 1, 4);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Pixel 6 ', 600, 5, '', 1, 4);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Pixel 6 Pro', 700, 5, '', 1, 4);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Pixel 7 ', 1000, 5, '', 1, 4);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Pixel 7 Pro', 1200, 5, '', 1, 4);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP FirstPhone Pro', 1100, 5, '', 1, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP SecondPhone', 1000, 5, '', 1, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP ThirdPhone', 1200, 5, '', 1, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP Watch LTZ', 100, 5, '', 4, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP Watch LTA', 200, 5, '', 4, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP Watch LTE', 300, 5, '', 4, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP Laptop 1', 2500, 5, '', 2, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP Laptop 2', 2700, 5, '', 2, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'SAMSUNG g3 Laptop', 2100, 5, '', 2, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'SAMSUNG g3s2 Laptop', 2100, 5, '', 2, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'SAMSUNG g33 Laptop', 2100, 5, '', 2, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'MacBook 3 pro', 3500, 5, '', 2, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'MacBook 2 pro', 1700, 5, '', 2, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'MacBook Lite', 1300, 5, '', 2, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'HP Laptop 3', 2100, 5, '', 2, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Pixel Watch LTE', 380, 5, '', 4, 4);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Pixel Watch wifi', 350, 5, '', 4, 4);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Apple Watch 5 Series', 650, 4, '', 4, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Apple Watch 4 Series', 450, 4, '', 4, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Galaxy Watch 3 pro', 370, 4, '', 4, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Galaxy Watch 4', 430, 4, '', 4, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'US123', 1230, 3, '', 3, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'US1dfg23', 1020, 3, '', 3, 1);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'First Apple TV', 1800, 3, '', 3, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Second Apple TV', 1800, 3, '', 3, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Third Apple TV', 1800, 3, '', 3, 2);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Third HP TV', 1800, 3, '', 3, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'Second HP TV', 1250, 3, '', 3, 3);
INSERT INTO devices(name, price, rating, img, type_id, brand_id) VALUES ( 'First HP TV', 1250, 3, '', 3, 3);
--
INSERT INTO baskets (user_id) VALUES (1);
INSERT INTO baskets (user_id) VALUES (2);
INSERT INTO baskets (user_id) VALUES (3);
--
-- INSERT INTO basket_devices (device_id) VALUES (4);
-- INSERT INTO basket_devices (device_id) VALUES (3);
-- INSERT INTO basket_devices (device_id) VALUES (2);
-- INSERT INTO basket_devices (device_id) VALUES (1);
--
INSERT INTO ratings (user_id, device_id, rate) VALUES (1, 1, 5);
INSERT INTO ratings (user_id, device_id, rate) VALUES (2, 3, 5);
INSERT INTO ratings (user_id, device_id, rate) VALUES (3, 5, 4);
-- --
INSERT INTO device_infos (device_id, title, description) VALUES (1,'camera', '48px');
-- INSERT INTO device_infos (device_id, title, description) VALUES (2,'camera', '108px');
-- INSERT INTO device_infos (device_id, title, description) VALUES (3,'camera', '64px');
-- INSERT INTO device_infos (device_id, title, description) VALUES (4,'memory', '2 GB');
-- INSERT INTO device_infos (device_id, title, description) VALUES (5,'memory', '1 GB');
INSERT INTO device_infos (device_id, title, description) VALUES (6,'memory', '1 GB');
-- --
-- --
-- --
