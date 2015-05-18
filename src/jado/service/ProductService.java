package jado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jado.dao.ProductCommentDao;
import jado.dao.ProductDao;
import jado.model.Product;
import jado.model.ProductComment;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductCommentDao productCommentDao;
	
	public Product getProduct(int productId) {
		return productDao.selectByPk(productId);
	}

	public List<ProductComment> getComments(int productId) {
		return productCommentDao.findByProduct(productId);
	}

	public boolean deleteProduct(int productId, String userId) {
		List<ProductComment> comments = productCommentDao.findByProduct(productId);
		if(canDelete(userId, comments)){
			productDao.remove(productId);
			return true;
		}
		return false;
	}

	private boolean canDelete(String userId, List<ProductComment> comments) {
		for (ProductComment productComment : comments) {
			if (!userId.equals(productComment.getUserId())) {
				return false;
			}
		}
		return true;
	}

}
