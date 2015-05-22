package pretest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import core.mail.UUIDGenerator;

public class TimeConvertTest {

	@Test
	public void 태호야_String타입의_오늘_날짜_포맷이_마음에_들지_않아서_바꾸고_싶어요() {
		DateFormat df = new SimpleDateFormat("yyMMdd", Locale.KOREAN);
		String beautifulToday = df.format(new Date());
		System.out.println("img-" + beautifulToday + "-" + UUIDGenerator.createUUID());
	}
}
