package sqlDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import command.Action;
import command.Word;

public class InitialData {
	public static List<Word> getWords() throws IOException {
		List<Word> wordList = new ArrayList<Word>();
		ReadCSV readWords = new ReadCSV("words.csv");
		try {
			readWords.next(); // skip headings
			while (true) {
				List<String> tuple = readWords.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String prime = i.next();
				String name = i.next();
				int type = Integer.parseInt(i.next());
				Word word = new Word(name, prime, type);
				wordList.add(word);
			}
			return wordList;
		} finally {
			readWords.close();
		}
	}
	
	public static List<Action> getActions() throws IOException {
		List<Action> actionList = new ArrayList<Action>();
		ReadCSV readActions = new ReadCSV("actions.csv");
		try {
			readActions.next(); // skip headings
			while (true) {
				List<String> tuple = readActions.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String name = i.next();
				Word verb = Word.makeWord(i.next(), 1);
				Word noun = Word.makeWord(i.next(), 2);
				int method = Integer.parseInt(i.next());
				Action action = new Action(name, verb, noun, method);
				actionList.add(action);
			}
			return actionList;
		} finally {
			readActions.close();
		}
	}
}
