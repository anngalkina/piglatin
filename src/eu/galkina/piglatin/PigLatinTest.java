package eu.galkina.piglatin;

import org.junit.Test;
import static org.junit.Assert.*;

public class PigLatinTest {

	@Test
	public void test0() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("").transform();
		assertEquals("", t.getTransformedText());
	}

	@Test
	public void test00() {
		PigLatinTextTransformer t = new PigLatinTextTransformer(null).transform();
		assertEquals("", t.getTransformedText());
	}

	
	@Test
	public void test1() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("Hello").transform();
		assertEquals("Ellohay", t.getTransformedText());
	}

	@Test
	public void test2() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("apple").transform();
		assertEquals("appleway", t.getTransformedText());
	}
	
	@Test
	public void test3() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("stairway").transform();
		assertEquals("stairway", t.getTransformedText());
	}

	@Test
	public void test4() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("can't").transform();
		assertEquals("antca'y", t.getTransformedText());
	}

	@Test
	public void test5() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("end.").transform();
		assertEquals("endway.", t.getTransformedText());
	}

	@Test
	public void test6() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("this-thing").transform();
		assertEquals("histay-hingtay", t.getTransformedText());
	}
	
	@Test
	public void test7() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("Beach").transform();
		assertEquals("Eachbay", t.getTransformedText());
	}
	
	@Test
	public void test8() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("McCloud").transform();
		assertEquals("CcLoudmay", t.getTransformedText());
	}
	
	@Test
	public void test9() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("Where are you from?").transform();
		assertEquals("Hereway areway ouyay romfay?", t.getTransformedText());
	}

	@Test
	public void test10() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("can’t").transform();
		assertEquals("antca’y", t.getTransformedText());
	}

	@Test
	public void test11() {
		PigLatinTextTransformer t = new PigLatinTextTransformer("One!? language, is never enough; My hovercraft: is full of \"eels\"...").transform();
		assertEquals("Oneway!? anguagelay, isway evernay enoughway; Ymay overcrafthay: isway ullfay ofway eel\"sway\"...", t.getTransformedText());
	}

}
