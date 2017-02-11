package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class RequiredWordsFilterTest {
	LineStorage inputLineStorage;
	LineStorage afterFilterLineStorage;
	RequiredWordsFilter filter;

	@Before
	public void setUp() {
		// TODO
		inputLineStorage = new LineStorage();
		afterFilterLineStorage = new LineStorage();
		filter = new RequiredWordsFilter(afterFilterLineStorage);
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("Day");
		requiredWords.add("Tomorrow");
		filter.setIgnoreWords(words);
		inputLineStorage.addObserver(filter);
	}

	@Test
	public void test() {
		// TODO
		inputLineStorage.addLine("The Day after Tomorrow");

		assertEquals("Day after Tomorrow the", afterFilterLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterFilterLineStorage.get(1).toString());
	}
}
