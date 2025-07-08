-- ユーザーテーブルデータ
INSERT INTO users(name,email,password) VALUES('student','student@aaa.com','himitu');

-- カテゴリーテーブルデータ
INSERT INTO categories(name) VALUES('主食');
INSERT INTO categories(name) VALUES('肉');
INSERT INTO categories(name) VALUES('魚介');
INSERT INTO categories(name) VALUES('卵');
INSERT INTO categories(name) VALUES('野菜・果物');
INSERT INTO categories(name) VALUES('その他');

-- 商品テーブルデータ
INSERT INTO food(category_id, foodname, quantity, memo, timelimit, user_id) VALUES(1, 'パン', 6 ,'', '2025-07-12','1' );
INSERT INTO food(category_id, foodname, quantity, memo, timelimit, user_id) VALUES(2, '鶏肉', 500 ,'', '2025-07-08','1' );
INSERT INTO food(category_id, foodname, quantity, memo, timelimit, user_id) VALUES(4, '卵', 12 ,'', '2025-07-15','1' );
INSERT INTO food(category_id, foodname, quantity, memo, timelimit, user_id) VALUES(5, 'レタス', 2 ,'', '2025-07-10','1' );
INSERT INTO food(category_id, foodname, quantity, memo, timelimit, user_id) VALUES(6, 'カルピス', 5 ,'', '2025-10-18','1' );

