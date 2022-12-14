SELECT * FROM orders;

ALTER TABLE orders AUTO_INCREMENT = 1;

DELETE FROM orders;

SELECT * FROM orders WHERE user_id IN (SELECT id FROM USER WHERe user.basic_address = "서울")