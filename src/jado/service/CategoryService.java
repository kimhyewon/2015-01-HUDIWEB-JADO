package jado.service;

import java.io.IOException;
import java.util.List;

import jado.dao.ArticleCommentDao;
import jado.dao.CategoryDao;
import jado.dao.ProductCommentDao;
import jado.dao.ProductDao;
import jado.model.Article;
import jado.model.ArticleComment;
import jado.model.Board;
import jado.model.Category;
import jado.model.FileInfo;
import jado.model.Product;
import jado.model.ProductComment;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.ForignKeyException;
import core.util.Upload;

@Service
public class CategoryService {
	@Autowired private CategoryDao categoryDao;
	@Autowired private ProductDao productDao;
	@Autowired private Upload upload;
	@Autowired private ProductCommentDao productCommentDao;
	
	public Category getCategory(int categoryId) {
		return categoryDao.selectByPk(categoryId);
	}
	
	public List<Product> getProducts(int categoryId) {
		return productDao.selectAllByCateGoryId(categoryId);
	}

	public Product getProduct(int productId) {
		return productDao.selectByPk(productId);
	}
	
	public List<ProductComment> getComments(int productId) {
		return productCommentDao.findByProduct(productId);
	}
	
	public void insertProduct(Product product) throws ForignKeyException {
		Category category = categoryDao.selectByPk(product.getCategoryId());
		if (category == null) {
			throw new ForignKeyException("잘못된 경로로 접근하셨습니다.");
		}
		productDao.insert(product);
	}

	public void insertproductCommnet(ProductComment productComment) throws ForignKeyException{
		Product product = productDao.selectByPk(productComment.getProductId());
		if(product == null) {
			throw new ForignKeyException("잘못된 경로로 접근하셨습니다.");
		}
		productCommentDao.insert(productComment);
	}

	public void deleteProductComment(int productId, String userId, String commentTime) throws ForignKeyException{
		productCommentDao.remove(productId, userId, commentTime);
	}
	
	
//	public void representImage(FileInfo fileInfo) throws IllegalStateException, IOException {
//		String url = fileInfo.getUrl();
//		upload.uploadFile(fileInfo.getFile(), fileInfo.getLocalLocation());
//
//		Product product = productDao.selectByUrl(url);
//		product.setImgUrl(url);
//		productDao.updateImageUrl(fileInfo);
//		
//	}
	
}
