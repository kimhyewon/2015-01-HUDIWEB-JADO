package pretest;

import static org.junit.Assert.assertEquals;

import java.time.temporal.ChronoField;
import java.util.Calendar;

import org.junit.Test;

public class JodaTimeStudy {

	@Test
	// 자바 기본은 1을 일요일로 생각 7을 토요일로 생각한다
	public void 자바기본날짜계산() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 9);
		cal.set(Calendar.YEAR, 1988);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		assertEquals("수", convertNumberToDayOfWeekForJavaNormal(dayOfWeek));
	}

	@Test
	// 자바 8은 1을 월요일로 생각 7을 일요일로 생각한다
	public void 자바8날짜계산() {
		// 방법 1 - String 형태의 요일이 나온다
		java.time.LocalDate date = java.time.LocalDate.of(2015, java.time.Month.MARCH, 12);
		java.time.DayOfWeek dayOfWeek = date.getDayOfWeek();
		assertEquals(java.time.DayOfWeek.THURSDAY, dayOfWeek);

		// 방법 2 - int 형태의 요일도 가져올 수 있다
		date = java.time.LocalDate.of(1988, java.time.Month.MARCH, 9);
		dayOfWeek = date.getDayOfWeek();
		int dayOfWeek2 = dayOfWeek.get(ChronoField.DAY_OF_WEEK);
		assertEquals(java.time.DayOfWeek.WEDNESDAY.getValue(), dayOfWeek2);

		// 방법 3 - 시간(시, 분)도 다룰 수 있다
		java.time.LocalDateTime localDateTime = java.time.LocalDateTime.of(2014, java.time.Month.APRIL, 4, 5, 4);
		java.time.DayOfWeek dayOfWeek3 = localDateTime.getDayOfWeek();
		assertEquals(java.time.DayOfWeek.FRIDAY, dayOfWeek3);
	}

	@Test
	public void 조다날짜계산() throws Exception {
		org.joda.time.DateTime dateTime = new org.joda.time.DateTime(1988, 3, 9, 0, 0);
		int dayOfWeek = dateTime.getDayOfWeek();
		assertEquals(org.joda.time.DateTimeConstants.WEDNESDAY, dayOfWeek);
	}

	private String convertNumberToDayOfWeekForJavaNormal(int nDay) {
		if (nDay < 1 || nDay > 7)
			return null;
		String[] dayOfWeeks = { "error", "일", "월", "화", "수", "목", "금", "토" };
		return dayOfWeeks[nDay];
	}
}
