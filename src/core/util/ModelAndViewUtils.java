package core.util;

import jado.model.Notice;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtils {

	// notice 가 있으면 notice 페이지로 이동
	public static ModelAndView render(String result, Notice notice, ModelAndView mav) {
		if (notice.getHeader() != null) {
			render("notice", notice);
		}
		mav.setViewName(result);
		return mav;
	}

	// notice 페이지로
	public static ModelAndView renderToNotice(Notice notice) {
		return render("notice", notice);
	}
	
	// noticeForSeller 페이지로
	public static ModelAndView renderToNoticeForSeller(Notice notice) {
		return render("noticeForSeller", notice);
	}
	
	public static ModelAndView render(String string, Notice notice) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", notice);
		mav.setViewName(string);
		return mav;
	}
}