-- 各種テーブル削除
Drop VIEW IF EXISTS v_food;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS categories;
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
   memo TEXT,
   timelimit DATE,
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

CREATE VIEW v_food AS
select
    shoku.id,
    cat.id as category_id,
    cat.name as categoryname,
    shoku.foodname,
    shoku.quantity,
    shoku.memo,
    shoku.timelimit,
    shoku.user_id
from
    food shoku
left outer join
    categories cat
on 
    shoku.category_id=cat.id