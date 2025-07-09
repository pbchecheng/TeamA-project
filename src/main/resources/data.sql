-- ユーザーテーブルデータ
INSERT INTO users(name,email,password) VALUES('student','student@aaa.com','himitu');

-- カテゴリーテーブルデータ
INSERT INTO categories(name) VALUES('主食');
INSERT INTO categories(name) VALUES('肉');
INSERT INTO categories(name) VALUES('魚介');
INSERT INTO categories(name) VALUES('卵');
INSERT INTO categories(name) VALUES('野菜・果物');
INSERT INTO categories(name) VALUES('飲み物');
INSERT INTO categories(name) VALUES('その他');

-- 数量単位テーブルデータ
INSERT INTO counts(name) VALUES('食分');
INSERT INTO counts(name) VALUES('g');
INSERT INTO counts(name) VALUES('切れ');
INSERT INTO counts(name) VALUES('枚');
INSERT INTO counts(name) VALUES('玉');
INSERT INTO counts(name) VALUES('本');
INSERT INTO counts(name) VALUES('');
INSERT INTO counts(name) VALUES('つ');
INSERT INTO counts(name) VALUES('パック');
INSERT INTO counts(name) VALUES('ケース');
INSERT INTO counts(name) VALUES('個');
INSERT INTO counts(name) VALUES('株');
INSERT INTO counts(name) VALUES('缶');
INSERT INTO counts(name) VALUES('合');
INSERT INTO counts(name) VALUES('貫');
INSERT INTO counts(name) VALUES('杯');
INSERT INTO counts(name) VALUES('ml');

-- 商品テーブルデータ
INSERT INTO food(category_id, foodname, quantity, count_id, memo, timelimit, user_id) VALUES(1, 'パン', 6 ,3,'', '2025-07-12','1' );
INSERT INTO food(category_id, foodname, quantity, count_id, memo, timelimit, user_id) VALUES(2, '鶏肉', 500 ,2,'', '2025-07-08','1' );
INSERT INTO food(category_id, foodname, quantity, count_id, memo, timelimit, user_id) VALUES(4, '卵', 12 ,4,'', '2025-07-15','1' );
INSERT INTO food(category_id, foodname, quantity, count_id, memo, timelimit, user_id) VALUES(5, 'レタス', 2 ,5,'', '2025-07-10','1' );
INSERT INTO food(category_id, foodname, quantity, count_id, memo, timelimit, user_id) VALUES(6, 'カルピス', 5 ,6,'', '2025-10-18','1' );

