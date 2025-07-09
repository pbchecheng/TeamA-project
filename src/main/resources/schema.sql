-- 各種テーブル削除
Drop VIEW IF EXISTS v_food;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS counts;
DROP TABLE IF EXISTS users;


-- カテゴリーテーブル
CREATE TABLE categories
(
   id SERIAL PRIMARY KEY,
   name TEXT
);

-- 数量単位テーブル
CREATE TABLE counts
(
   id SERIAL PRIMARY KEY,
   name TEXT
);

-- 商品テーブル
CREATE TABLE food
(
   id SERIAL PRIMARY KEY,
   category_id INTEGER,
   FOREIGN KEY (category_id) REFERENCES categories(id),
   foodName TEXT,
   quantity INTEGER,
   count_id INTEGER,
   FOREIGN KEY (count_id) REFERENCES counts(id),
   memo TEXT,
   timeLimit DATE,
   user_id INTEGER
);



-- 顧客テーブル
CREATE TABLE users
(
   id SERIAL PRIMARY KEY,
   name TEXT,
   email TEXT,
   password TEXT
);