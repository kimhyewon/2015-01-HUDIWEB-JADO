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

insert into SHOP VALUES('testurl', 'HEY, JADO', '031-737-7979', default, default, 'footer', default, default, default, default);
insert into SELLER VALUES('testurl', 'seller','우리','bankaccount');

insert into BOARD VALUES(null, 'testurl', 'INFO');

insert into ARTICLE VALUES( null, 1, '오픈 기념 이벤트', 'content', null);
insert into ARTICLE VALUES( null, 1, '봄 신상 할인 이벤트', 'content', null);
insert into ARTICLE VALUES( null, 1, '직원 모집', 'content', null);
insert into ARTICLE_COMMENT VALUES( 1, 'customer', null, 'contents');

insert into CATEGORY VALUES(null, 'OUTER', 'testurl');
insert into CATEGORY VALUES(null, 'TOP', 'testurl');
insert into CATEGORY VALUES(null, 'DRESS', 'testurl');
insert into CATEGORY VALUES(null, 'PANTS', 'testurl');
insert into CATEGORY VALUES(null, 'SKIRT', 'testurl');
insert into CATEGORY VALUES(null, 'ACC', 'testurl');

insert into PRODUCT VALUES(null, 1, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/9.jpg','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Pleats-droped<br>Shift Dress', 12000, 100,'/userImg/product/img/8.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Pleats-droped<br>Shift Dress', 15000, 100,'/userImg/product/img/7.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Pleats-droped<br>Shift Dress', 12000, 100,'/userImg/product/img/6.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Pleats-droped<br>Shift Dress', 14000, 100,'/userImg/product/img/5.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Pleats-droped<br>Shift Dress', 18000, 100,'/userImg/product/img/4.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Shift Dress', 10000, 100,'/userImg/product/img/3.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Shift Dress', 13000, 100,'/userImg/product/img/2.gif','OPS2099/Color Block<br>Pleats-droped<br>Shift Dress', null);
insert into PRODUCT VALUES(null, 1, 'Shift Dress', 14000, 100,'/userImg/product/img/1.gif', '대박 좋아 좋아좋아', null);
--insert into CART_has_PRODUCT values('testShopUrl', 'user', 1, '1');
