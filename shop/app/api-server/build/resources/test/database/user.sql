SELECT * FROM user;

DELETE FROM user;

ALTER TABLE user AUTO_INCREMENT = 1;

UPDATE user SET basic_address = '서울' WHERE id <= 5;

UPDATE user SET basic_address = '경기도' WHERE id >= 6;

SELECT u.* from user AS u INNER JOIN orders AS o ON u.id = o.user_id;


SET GLOBAL log_bin_trust_function_creators = 1;

DROP FUNCTION IF EXISTS SelectUser;

CREATE PROCEDURE SelectAllUserPro(ReqName varchar(30))
BEGIN
    SELECT * FROM user WHERE name = ReqName;
END;

DROP PROCEDURE SelectAllUserPro;

CREATE FUNCTION SelectUser(basic varchar(50))
    RETURNS INT
BEGIN
    DECLARE Result INT;

    SELECT count(*) INTO Result
    FROM user
        WHERE basic_address = basic;

    RETURN Result;
END;

CALL SelectAllUserPro("테스트1");

SELECT SelectUser("서울");