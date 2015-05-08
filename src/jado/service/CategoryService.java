package jado.service;

import java.util.List;

import jado.dao.CategoryDao;
import jado.dao.ProductDao;
import jado.model.Article;
import jado.model.Board;
import jado.model.Category;
import jado.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.ForignKeyException;

@Service
public class CategoryService {
	@Autowired private CategoryDao categoryDao;
	@Autowired private ProductDao productDao;
	
	public Category getCategory(int categoryId) {
		return categoryDao.selectByPk(categoryId);
	}
	
	public List<Product> getProducts(int categoryId) {
		return productDao.selectAllByCateGoryId(categoryId);
	}

	public void insertProduct(Product product) throws ForignKeyException {
		Category category = categoryDao.selectByPk(product.getCategoryId());
		if (category == null) {
			throw new ForignKeyException("잘못된 경로로 접근하셨습니다.");
		}
		productDao.insert(product);
	}
	
}
