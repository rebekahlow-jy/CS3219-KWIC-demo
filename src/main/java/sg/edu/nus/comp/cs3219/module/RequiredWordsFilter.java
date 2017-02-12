package sg.edu.nus.comp.cs3219.module;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import sg.edu.nus.comp.cs3219.event.LineStorageChangeEvent;
import sg.edu.nus.comp.cs3219.model.Line;
import sg.edu.nus.comp.cs3219.model.LineStorage;

public class RequiredWordsFilter implements Observer {
	final private LineStorage resultStorage;
	private Set<String> requiredWords = new HashSet<>();

	public RequiredWordsFilter(LineStorage storage) {
		resultStorage = storage;
	}
	
	public void setRequiredWords(Set<String> requiredWordSet) {
		requiredWords = requiredWordSet;
	}

	@Override
	public void update(Observable o, Object arg) {
		LineStorage storage = (LineStorage) o;
		LineStorageChangeEvent event = (LineStorageChangeEvent) arg;
		switch (event.getEventType()) {
		case ADD:
			Line line = storage.get(event.getChangedLine());
			// TODO: add filtered result to result storage
			if(!requiredWords.contains("")) {
				filter(event.getChangedLine());
			}
			break;
		default:
			break;
		}
	}
	
	private void filter(int lineNumber) {
		for (int i = 0; i <= lineNumber && i < resultStorage.size(); i++) {
			if (!isRequiredWord(resultStorage.get(i).getWord(0))) {
				resultStorage.delete(i);
				break;
			}
		}
	}
	
	private boolean isRequiredWord(String word) {
		return requiredWords.contains(word.toLowerCase());
	}
}
