package jado.dao;

import static org.junit.Assert.*;
import jado.model.NormalUser;
import jado.model.Seller;

import org.junit.Test;

public class UserDaoTest {

	@Test
	public void test() {
		NormalUser user = new NormalUser("aaaa", "1111", "이경륜" , "01027723883", "경기도 안양시 동안구 비산1동" );
		UserDao.insert(user);
	}
	@Test
	public void test2(){
		Seller user = new Seller("aaaa", "1111", "이경륜" , "01027723883", "경기도 안양시 동안구 비산1동", "erin", "0102222222", "sss", "sss2");
		UserDao.insert(user);
	}
}
