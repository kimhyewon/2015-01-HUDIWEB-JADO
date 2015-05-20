package jado.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.InsertTargetRecordNotFoundException;
import core.exception.NotExistFileException;
import core.util.Upload;
import jado.dao.ProductCommentDao;
import jado.dao.ProductDao;
import jado.model.FileInfo;
import jado.model.Product;
import jado.model.ProductComment;

@Service
public class ProductService {
	@Autowired
	private Upload upload;
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

	public void representImage(FileInfo fileInfo, Product product) throws IllegalStateException, IOException, NotExistFileException {
		String url = fileInfo.getLocalLocation();
		upload.uploadFile(fileInfo.getFile(), fileInfo.getLocalLocation());

		product.setImgUrl(url);
		productDao.insert(product);
	}

	public boolean deleteProduct(int productId, String userId) {
		List<ProductComment> comments = productCommentDao.findByProduct(productId);
		if (canDelete(userId, comments)) {
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

	public void updateImage(FileInfo fileInfo) throws IllegalStateException, IOException, NotExistFileException {
		upload.uploadFile(fileInfo.getFile(), fileInfo.getLocalLocation());
	}

	public void updateProduct(Product product) {
		Product productFromDao = productDao.selectByPk(product.getId());
		if (productFromDao == null) throw new InsertTargetRecordNotFoundException("product 가 없습니다.");
		if(!productFromDao.update(product)) throw new InsertTargetRecordNotFoundException("바꿀 정보가 없습니다");
		productDao.update(product);
	}

	public void insertProductCommnet(ProductComment productComment) {
		Product product = productDao.selectByPk(productComment.getProductId());
		if (product == null) {
			throw new InsertTargetRecordNotFoundException("상품 댓글 등록 대상 상품이 존재하지 않습니다");
		}
		productCommentDao.insert(productComment);
	}

	public void deleteProductComment(ProductComment productComment) {
		productCommentDao.removeByPk(productComment);
	}
}
