package jado.controller;

import jado.model.Notice;
import jado.model.ProductComment;
import jado.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import core.exception.InsertTargetRecordNotFoundException;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/api")
public class ProductCommentController {
	private static final Logger logger = LoggerFactory.getLogger(ProductCommentController.class);
	@Autowired
	ProductService productService;

	// 댓글 등록 구현
	@RequestMapping(value = "/comment/create", method = RequestMethod.POST)
	public ModelAndView commentPost(ProductComment productComment) {
		logger.debug("create :{}", productComment.getProductId());
		try {
			productService.insertProductCommnet(productComment);
		} catch (InsertTargetRecordNotFoundException e) {
			logger.debug("failed"+e.getMessage());
			return ModelAndViewUtils.renderToNotice(new Notice("Fail", e.getMessage()));
		}
		return new ModelAndView("redirect:/");
	}
	
	// 댓글 삭제 구현
	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
	public ModelAndView commentDeletePost(ProductComment productComment) {
		logger.debug("delete :{}", productComment.getProductId());
		try {
			productService.deleteProductComment(productComment);
		} catch (Exception e) {
			logger.debug("failed"+e.getMessage());
			return ModelAndViewUtils.renderToNotice(new Notice("Fail", e.getMessage()));
		}
		return new ModelAndView("redirect:/");
	}

}
