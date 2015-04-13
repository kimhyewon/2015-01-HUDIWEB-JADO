-- Test Set

-- -----------------------------------------------------
-- Table `jado_dev`.`USER`
-- -----------------------------------------------------

-- 아이디 123, 비밀번호 123 Test User
insert into USER VALUES('123', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','F');
insert into USER VALUES('erin314@naver.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T');

insert into USER VALUES('testuser2@testuser.com', 'testuserPassword','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','F');
insert into SHOP VALUES('testurl', 'testtitle,','testphone', '/userImg/shop/main/default.png','/userImg/shop/main/default.png' ,'/userImg/shop/main/default.png','thema1','footer_test');
insert into SELLER VALUES('testurl', 'testuser2@testuser.com','bank','bankaccount');

insert into BOARD VALUES('testurl', '공지사항');
insert into ARTICLE VALUES('testurl', '공지사항','오픈 기념 이벤트','content', '2015-03-26 12:34:56');