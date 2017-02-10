package sg.edu.nus.comp.cs3219.control;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sg.edu.nus.comp.cs3219.model.LineStorage;
import sg.edu.nus.comp.cs3219.module.Alphabetizer;
import sg.edu.nus.comp.cs3219.module.CircularShifter;
import sg.edu.nus.comp.cs3219.module.RequiredWordsFilter;

public class MasterControl {
	final private Alphabetizer alphabetizer;
	final private CircularShifter shifter;
	final private RequiredWordsFilter filter;

	private LineStorage rawInputLines;
	private LineStorage resultLines;

	public MasterControl() {
		// Storage
		rawInputLines = new LineStorage();
		resultLines = new LineStorage();

		// Sub-modules
		shifter = new CircularShifter(resultLines);
		filter = new RequiredWordsFilter(resultLines);
		alphabetizer = new Alphabetizer();
		
		// Set up observation
		rawInputLines.addObserver(shifter);
		resultLines.addObserver(filter);
		resultLines.addObserver(alphabetizer);	
	}

	public List<String> run(List<String> input, Set<String> ignoredWords, Set<String> requiredWords) {
		rawInputLines.clearLines();
		resultLines.clearLines();

		// Set ignore and required words (make them lowercase for comparison)
		shifter.setIgnoreWords(this.transformSetToLowercase(ignoredWords));
		filter.setRequiredWords(this.transformSetToLowercase(requiredWords));
		
		// Add data line by line
		for (String line : input) {
			rawInputLines.addLine(line);
		}
		
		return resultLines.getAll();
	}
	
	private Set<String> transformSetToLowercase(Set<String> set) {
		return set.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
	}
}
