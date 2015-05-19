package jado.service;

import jado.dao.CategoryDao;
import jado.dao.ProductCommentDao;
import jado.dao.ProductDao;
import jado.model.Category;
import jado.model.FileInfo;
import jado.model.Product;
import jado.model.ProductComment;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.InsertTargetRecordNotFoundException;
import core.exception.NotExistFileException;
import core.util.Upload;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private Upload upload;
	@Autowired
	private ProductCommentDao productCommentDao;

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

	public void insertProduct(Product product) throws InsertTargetRecordNotFoundException {
		Category category = categoryDao.selectByPk(product.getCategoryId());
		if (category == null) {
			throw new InsertTargetRecordNotFoundException("상품 등록대상 카테고리가 존재하지 않습니다.");
		}
		productDao.insert(product);
	}

	public void insertproductCommnet(ProductComment productComment) throws InsertTargetRecordNotFoundException {
		Product product = productDao.selectByPk(productComment.getProductId());
		if (product == null) {
			throw new InsertTargetRecordNotFoundException("상품 댓글 등록 대상 상품이 존재하지 않습니다");
		}
		productCommentDao.insert(productComment);
	}

	public void deleteProductComment(int productId, String userId, String commentTime) {
		productCommentDao.remove(productId, userId, commentTime);
	}

	public void deleteProduct(int productId) {
		productDao.remove(productId);
	}

	public void updateProduct(Product product) {
		productDao.update(product);
	}

	public void representImage(FileInfo fileInfo, Product product) throws IllegalStateException, IOException, NotExistFileException {
		String imgUrl = fileInfo.getLocalLocation();
		upload.uploadFile(fileInfo.getFile(), fileInfo.getLocalLocation());

		product.setImgUrl(imgUrl);
		productDao.insert(product);
	}

}
