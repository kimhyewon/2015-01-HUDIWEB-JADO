package pretest;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringMapping {

	@Test
	public void test() {
		String a ="a";
		String h = "hello ";
		h=h.replace("e", a);
		System.out.print(h);
	}

}
