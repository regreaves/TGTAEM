package sqlDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import command.Action;
import command.Word;
import objects.Item;
import objects.NPC;
import objects.Room;

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

	public static List<Room> getRooms() throws IOException {
		List<Room> roomList = new ArrayList<Room>();
		ReadCSV readRooms = new ReadCSV("map.csv");
		try {
			readRooms.next(); // skip headings
			while (true) {
				List<String> tuple = readRooms.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String id = i.next();
				String name = i.next();
				String dscrpt = i.next();
				boolean visited = false;
				int x = Integer.parseInt(i.next());
				if (x == 1) {
					visited = true;
				}
				ArrayList<String> c = new ArrayList<>();
				c.add(i.next()); // north
				c.add(i.next()); // northeast
				c.add(i.next()); // east
				c.add(i.next()); // southeast
				c.add(i.next()); // south
				c.add(i.next()); // southwests
				c.add(i.next()); // west
				c.add(i.next()); // northwest
				c.add(i.next()); // up
				c.add(i.next()); // down
				Room r = new Room(id, name, dscrpt, visited, c);
				roomList.add(r);
			}
			return roomList;
		} finally {
			readRooms.close();
		}
	}

	public static List<Item> getItems() throws IOException {
		List<Item> itemList = new ArrayList<Item>();
		ReadCSV readItems = new ReadCSV("items.csv");
		try {
			readItems.next(); // skip headings
			while (true) {
				List<String> tuple = readItems.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String id = i.next();
				String name = i.next();
				String init_dscrpt = i.next();
				String invent_dscrpt = i.next();
				boolean isHidden = false;
				boolean moved = false;
				boolean vowel = false;
				boolean plural = false;
				int x = Integer.parseInt(i.next());
				if (x == 1) {
					isHidden = true;
				}
				x = Integer.parseInt(i.next());
				if (x == 1) {
					moved = true;
				}
				x = Integer.parseInt(i.next());
				if (x == 1) {
					vowel = true;
				}
				x = Integer.parseInt(i.next());
				if (x == 1) {
					plural = true;
				}
				int itemWeight = Integer.parseInt(i.next());
				Item item = new Item(id, name, init_dscrpt, invent_dscrpt, isHidden, moved, vowel, plural, itemWeight);
				itemList.add(item);
			}
			return itemList;
		} finally {
			readItems.close();
		}
	}
	
	public static List<NPC> getNPCs() throws IOException {
		List<NPC> npcList = new ArrayList<NPC>();
		ReadCSV readNPCs = new ReadCSV("npcs.csv");
		try {
			readNPCs.next(); // skip headings
			while (true) {
				List<String> tuple = readNPCs.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String id = i.next();
				String name = i.next();
				int health = Integer.parseInt(i.next());
				int attack = Integer.parseInt(i.next());
				int defense = Integer.parseInt(i.next());
				String description = i.next();
				NPC npc = new NPC(id, name, health, attack, defense, description);
				npcList.add(npc);
			}
			return npcList;
		} finally {
			readNPCs.close();
		}
	}


	public static List<Pair<String, String>> getItemLoc() throws IOException {
		List<Pair<String, String>> itemMap = new ArrayList<>();
		ReadCSV readItems = new ReadCSV("items_loc.csv");
		try {
			readItems.next(); // skip headings
			while (true) {
				List<String> tuple = readItems.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String id = i.next();
				String loc = i.next();
				Pair<String, String> p = new Pair<>(id, loc);
				itemMap.add(p);
			}
			return itemMap;
		} finally {
			readItems.close();
		}
	}
	
	public static List<Pair<String, String>> getNPCLoc() throws IOException {
		List<Pair<String, String>> npcMap = new ArrayList<>();
		ReadCSV readNPCs = new ReadCSV("npcs_loc.csv");
		try {
			readNPCs.next(); // skip headings
			while (true) {
				List<String> tuple = readNPCs.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String id = i.next();
				String loc = i.next();
				Pair<String, String> p = new Pair<>(id, loc);
				npcMap.add(p);
			}
			return npcMap;
		} finally {
			readNPCs.close();
		}
	}

	public static List<Pair<String, String>> getItemActions() throws IOException {
		List<Pair<String, String>> itemMap = new ArrayList<>();
		ReadCSV readItems = new ReadCSV("items_actions.csv");
		try {
			readItems.next(); // skip headings
			while (true) {
				List<String> tuple = readItems.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String id = i.next();
				String action = i.next();
				Pair<String, String> p = new Pair<>(id, action);
				itemMap.add(p);
			}
			return itemMap;
		} finally {
			readItems.close();
		}
	}

}
