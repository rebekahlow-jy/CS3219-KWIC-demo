package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.Line;
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
		afterFilterLineStorage.addLine("The Day after Tomorrow");
		afterFilterLineStorage.addLine("Day after Tomorrow the");
		afterFilterLineStorage.addLine("Tomorrow the Day after");
		
		filter = new RequiredWordsFilter(afterFilterLineStorage);
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("Day");
		requiredWords.add("Tomorrow");
		filter.setRequiredWords(requiredWords);

		inputLineStorage.addObserver(filter);		
	}

	@Test
	public void test() {
		// TODO
		inputLineStorage.addLine("The Day after Tomorrow");

		assertEquals(2, afterFilterLineStorage.size());
		assertEquals("Day after Tomorrow the", afterFilterLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterFilterLineStorage.get(1).toString());
	}
}
