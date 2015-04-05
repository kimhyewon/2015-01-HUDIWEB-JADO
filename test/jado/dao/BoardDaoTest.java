package jado.dao;

import jado.model.Board;

import org.junit.Test;

public class BoardDaoTest {

	@Test
	public void test() {
		Board board = new Board("testUrl", "직원구함");
		BoardDao.insert(board);
		Board board2 = BoardDao.select(board);
		
		System.out.println(board);
		System.out.println(board2);
	}

}
