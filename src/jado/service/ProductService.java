package jado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jado.dao.CategoryDao;
import jado.dao.ProductCommentDao;
import jado.dao.ProductDao;
import jado.model.Category;
import jado.model.Product;
import jado.model.ProductComment;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductCommentDao productCommentDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public Product getProduct(int productId) {
		return productDao.selectByPk(productId);
	}

	public List<ProductComment> getComments(int productId) {
		return productCommentDao.findByProduct(productId);
	}

	public Category getCategory(int categoryId) {
		return categoryDao.selectByPk(categoryId);
	}

}
