-- 各種テーブル削除
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS users;


-- カテゴリーテーブル
CREATE TABLE categories
(
   id SERIAL PRIMARY KEY,
   name TEXT
);

-- 商品テーブル
CREATE TABLE food
(
   id SERIAL PRIMARY KEY,
   category_id INTEGER,
   foodname TEXT,
   quantity INTEGER,
   timelimit DATE
);

-- 顧客テーブル
CREATE TABLE users
(
   id SERIAL PRIMARY KEY,
   name TEXT,
   email TEXT,
   password TEXT
);