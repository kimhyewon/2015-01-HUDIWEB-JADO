package core.exception;

/*
 * 예외 처리전략
 *  - 스프링책 1권 4장을 보길 권합니다.
*
 * 4.1.4, 예외 처리 전략
 * 
 * 체크 예외시 복구 불가 --> 런타임 예외
 * 
 * 과거 자바로 애플릿 같은 독립 어플리케이션 환경에서
 *  - 통제 불가능한 시스템 예외라도 어플리케이션 작업이 종료되지 않도록 하고 상황을 복구해야했음.
 *  
 * 자바 엔터프라이즈 환경은 다름
 *  - 수많은 요청 중 예외가 발생한 요청만 중단하면 됨.
 *  - 오히려 서버를 중단하고 사용자와 커뮤니케이션하면서 예외상황을 복구할 수 없음
 *  - 차라리 어플리케이션 차원에서 예외상황을 미리 파악하고 예외가 발생하지 않도록 차단하는 것이 좋음
 *  - 그리고 프로그램, 외부완경 오류 발생시 빨리 작업 취소 후 담당자 통보가 좋음
 * 
 * 자바 환경이 서버로 이동하면서 체크 예외 활동도의 가치는 낮아짐
 *  - 자칫 throws Exception으로 의미없는 메소드만 낳을 뿐.
 * 
 * 차라리, 빨리 Runtime Exception으로 전환해서 던지는 것이 좋음
 * 최근엔 체크예외 대신, 언체크 예외로 정의하는 것일 일반화
 *  - 예전엔 복구할 가능성이 조금이라도 존재시 체크 예외로 만든다고 생각했음
 *  - 하지만 지금은 항상 복구할 수 있는 예외가 아니라면 일단 언체크로 만드는 경향이 있음 
 *  
 *
 * 뭐..그렇다고 합니다..
 *
 *  
 *  1. 참고자료
 *   - [Dec7 study] https://github.com/cocagolau/Dec7_Study_Spring_Toby_140909/blob/ch4.1.5/practice/tobyspring/src/main/java/me/dec7/user/dao/UserDao.java
 *   - http://himitu87.tistory.com/46
 */
public class DuplicateUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateUserException(String message) {
		super(message);
	}
}
