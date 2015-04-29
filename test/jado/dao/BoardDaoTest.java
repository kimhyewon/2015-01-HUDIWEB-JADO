package jado.dao;


import java.util.List;

import jado.model.Board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/applicationContext.xml","file:webapps/WEB-INF/jado-servlet.xml"})
public class BoardDaoTest {
	private static final Logger log = LoggerFactory.getLogger(BoardDaoTest.class);

	@Autowired
	private BoardDao boardDao;

	@Test
	public void insertTest() {
		Board board = new Board("testUrl", "직원구함");
		boardDao.insert(board);
		board = boardDao.selectByPk(1);
		log.debug("board : {}", board);
		List<Board> boards = boardDao.selectAllByUrl(board.getShopUrl());
		log.debug("boards : {}", board);
		log.debug("boards : {}", boards);

	}

	@Test
	public void removeTest() throws Exception {
		Board board = new Board("testUrl", "할인 이벤트");
		boardDao.insert(board);
		boardDao.remove(2);
		board = boardDao.selectByPk(2);
		if (board == null) {
			log.debug("removed");
		}

	}

}
