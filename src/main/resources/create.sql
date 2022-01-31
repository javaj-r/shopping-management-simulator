/* database creating

DROP DATABASE IF EXISTS shopping;

CREATE DATABASE shopping
    WITH
    ENCODING = 'UTF8';

CREATE SCHEMA IF NOT EXISTS public;

SET SEARCH_PATH TO shopping, public;
*/


-- DROP TABLE IF EXISTS admin;

CREATE TABLE IF NOT EXISTS admin
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100)
);


-- DROP TABLE IF EXISTS category;

CREATE TABLE IF NOT EXISTS category
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(100),
    parent_id INTEGER
);


-- DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product
(
    id          SERIAL PRIMARY KEY,
    price       INTEGER,
    category_id INTEGER,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id)
);


-- DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS customer
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(100),
    password      VARCHAR(100),
    firstname     VARCHAR(100),
    lastname      VARCHAR(100),
    national_code BIGINT,
    email         VARCHAR(100),
    phone_number  BIGINT
);


-- DROP TABLE IF EXISTS cart;

CREATE TABLE IF NOT EXISTS cart
(
    id           SERIAL PRIMARY KEY,
    address      TEXT,
    phone_number BIGINT,
    customer_id  INTEGER,
    CONSTRAINT fk_cart_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);


-- DROP TABLE IF EXISTS cart;

CREATE TABLE IF NOT EXISTS cart_product
(
    id         SERIAL PRIMARY KEY,
    cart_id    INTEGER,
    product_id INTEGER,
    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id)
);