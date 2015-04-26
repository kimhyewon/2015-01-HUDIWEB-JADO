package jado.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.util.Upload;
import jado.dao.BoardDao;
import jado.dao.CategoryDao;
import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Board;
import jado.model.Category;
import jado.model.Seller;
import jado.model.Shop;
import jado.model.FileInfo;

@Service
public class ShopService {
	private static final Logger logger = LoggerFactory.getLogger(ShopService.class);
	@Autowired private ShopDao shopDao;
	@Autowired private BoardDao boardDao;
	@Autowired private CategoryDao categoryDao;
	@Autowired private UserDao userDao;
	@Autowired private Upload upload;

	public Shop setting(String userId) {
		if (userId == null) {
			// TODO login페이지가서 로그인 하도록 해야함
			return null;
		}
		Seller seller = userDao.selectSellerById(userId);
		if (seller == null)
			return null;
		Shop shop = shopDao.selectByUrl(seller.getShopUrl());
		shop.setBoards(boardDao.selectAllByUrl(shop.getUrl()));
		shop.setCategorys(categoryDao.selectAllByUrl(shop.getUrl()));
		return shop;
	}

	public Shop settingEditInfo(Shop shopFromClient) {
		Shop shop = shopDao.selectByUrl(shopFromClient.getUrl());
		if (shop.updateFromSettingPage(shopFromClient)) {
			shopDao.update(shop);
		}
		return shop;
	}

	public void settingEditImage(FileInfo uploadFile) throws IllegalStateException, IOException {
		String url = uploadFile.getUrl();
		String localLocation = uploadFile.getFileType() + "/" + url + ".jpg";
		upload.uploadFile(uploadFile.getFile(), localLocation);

		Shop shop = shopDao.selectByUrl(url);
		shop.setLogo_url(url);
		shopDao.update(shop);
	}

	public void boardDelete(Board board) {
		int countOfArticle = boardDao.countArticles(board);
		if (countOfArticle == 0) {
			boardDao.remove(board);
		}else{
			logger.debug(" article을 "+countOfArticle+"개 삭제하세요!");
		}
	}

	public void boardInsert(List<String> boards, String shopUrl) {
		for (String boardName : boards) {
			//TODO boardName 이 같으면, null이면 어떻게 할까? client 에서 확인합시다
			boardDao.insert(new Board(shopUrl, boardName));
		}
	}

	public void categoryDelete(Category category) {
		int countOfArticle = categoryDao.countProduct(category.getId());
		if (countOfArticle == 0) {
			categoryDao.remove(category.getId());
		}else{
			logger.debug(" product :"+countOfArticle+"개를 삭제하세요!");
		}
	}

	public void categoryInsert(List<String> categorys, String shopUrl) {
		for (String categoryName : categorys) {
			//TODO boardName 이 같으면, null이면 어떻게 할까? client 에서 확인합시다
			categoryDao.insert(new Category(categoryName, shopUrl));
		}
	}

}
