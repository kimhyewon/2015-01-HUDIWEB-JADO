package jado.controller;

import jado.service.RemoveUserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RemoveUserController  {
	
	@Autowired private RemoveUserService removeUserService;
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public String viewRemoveUserPage() {
		// TODO [우선순위 : 보통] - 이부분은 추후 블로그 개설이 가능해지게 되면 수정이 필요한 부분입니다.
		return "blogDummy";
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String processUserDelete(@RequestParam("userId") String userId, HttpSession session) {
		if(session.getAttribute("isSeller") != null){
			removeUserService.removeSeller(userId);
		}
		removeUserService.removeCustomer(userId);
		
		// TODO [우선순위 : 보통] - 회원 탈퇴 후에는 Session에 있는 모든 정보를 삭제하는것이 더 좋을것 같다는 생각이 듬
		// 또한 그냥 blog로 돌아가는것이 아니라 회원탈퇴가 성공적으로 처리되었음을 알려주는 페이지로 이동하는것이 좋을 듯
		session.invalidate();
		return "blogDummy";
	}
}
