package core.util;

import jado.model.Notice;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtils {

	// notice 가 있으면 notice 페이지로 이동
	public static ModelAndView render(String result, Notice notice, ModelAndView mav) {
		if (notice.getTitle() != null) {
			return renderToNotice(notice);
		}
		mav.setViewName(result);
		return mav;
	}

	public static ModelAndView render(String string, Notice notice) {
		ModelAndView mav = new ModelAndView();
		return render(string, notice, mav);
	}

	// notice 페이지로
	public static ModelAndView renderToNotice(Notice notice) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", notice);
		mav.setViewName("notice");
		return mav;
	}
}