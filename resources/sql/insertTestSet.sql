-- Test Set

-- -----------------------------------------------------
-- Table `jado_dev`.`USER`
-- -----------------------------------------------------

-- 권한 종류
--		ROLE_EMAIL_NOT_VERIFIED_USER : 이메일 인증이 되지 않은 회원
--		ROLE_CUSTOMER : 이메일 인증이 되었고 구매자로 가입한 회원
--		ROLE_SELLER : 이메일 인증이 되었고 판매자로 가입한 회원
--		ROLE_DEVELOPER : 개발할 떄 사용할
		
-- 비밀번호 = 1
insert into USER VALUES('user', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);
insert into USER VALUES('customer', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);
insert into USER VALUES('seller', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);
insert into USER VALUES('developer', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','name','phone','address','2015-03-26 12:34:56','2015-03-26 12:34:56','T', 1);

insert into USER_ROLE VALUES('user', 'ROLE_EMAIL_NOT_VERIFIED_USER');
insert into USER_ROLE VALUES('customer', 'ROLE_CUSTOMER');
insert into USER_ROLE VALUES('seller', 'ROLE_SELLER');
insert into USER_ROLE VALUES('developer', 'ROLE_DEVELOPER');

insert into SHOP VALUES('testurl', 'HEY, JODO', '031-737-7979', '/userImg/shop/main/default.png','/userImg/shop/main/main_img.jpg' ,'/userImg/shop/main/default.png', '경기도 성남시 분당구 삼평동 681 H스퀘어 N동 4층 경기도 성남시 분당구 판교역로 235 에이치스퀘어 엔동', 'thema1');
insert into SELLER VALUES('testurl', 'seller','우리','bankaccount');

insert into BOARD VALUES('testurl', 'INFO');
insert into ARTICLE VALUES('testurl', 'INFO','오픈 기념 이벤트','content', '2015-03-26 12:34:56');

insert into CATEGORY VALUES(null, 'OUTER', 'testurl');
insert into CATEGORY VALUES(null, 'TOP', 'testurl');
insert into CATEGORY VALUES(null, 'DRESS', 'testurl');
insert into CATEGORY VALUES(null, 'PANTS', 'testurl');
insert into CATEGORY VALUES(null, 'SKIRT', 'testurl');
insert into CATEGORY VALUES(null, 'ACC', 'testurl');
insert into PRODUCT VALUES(null, 1, '예쁜 옷', 1000, 100, '/img.jpg', 'lulu lala', null);