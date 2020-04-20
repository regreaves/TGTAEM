package sqlDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import command.Action;
import command.Word;
import objects.Item;
import objects.NPC;
import objects.Player;
import objects.Room;

public class InitialData {
	
	//R
	public static List<Pair<String, String>> getShortcuts() throws IOException {
		List<Pair<String, String>> shortcutList = new ArrayList<>();
		ReadCSV readShortcuts = new ReadCSV("shortcuts.csv");
		try {
			readShortcuts.next(); // skip headings
			while (true) {
				List<String> tuple = readShortcuts.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String shortcut = i.next();
				String action = i.next();
				Pair<String, String> p = new Pair<>(shortcut, action);
				shortcutList.add(p);
			}
			return shortcutList;
		} finally {
			readShortcuts.close();
		}
	}
	
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
				Room r = new Room();
				r.setID(id);
				r.setDisplayName(name);
				r.setDescription(dscrpt);
				r.setVisited(visited);
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
				
				Item item = new Item();
				
				item.setID(i.next());
				item.setName(i.next());
				item.setInitDscrpt(i.next());
				item.setInventDscrpt(i.next());				
				boolean hidden = false;
				boolean moved = false;
				boolean vowel = false;
				boolean plural = false;
				int x = Integer.parseInt(i.next());
				if (x == 1) {
					hidden = true;
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
				item.setHidden(hidden);
				item.setMoved(moved);
				item.setVowel(vowel);
				item.setPlural(plural);
				item.setWeight(Integer.parseInt(i.next()));
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
				NPC npc = new NPC();
				npc.setID(i.next());
				npc.setName(i.next());
				npc.setHealth(Integer.parseInt(i.next()));
				npc.setAttack(Integer.parseInt(i.next()));
				npc.setDefense(Integer.parseInt(i.next()));
				npc.setDescription(i.next());
				
				npcList.add(npc);
			}
			return npcList;
		} finally {
			readNPCs.close();
		}
	}


	public static List<Pair<String, String>> getItemMap() throws IOException {
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
	
	public static List<Pair<String, String>> getNPCMap() throws IOException {
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
	
	public static List<Player> getPlayers() throws IOException {
		List<Player> playerList = new ArrayList<Player>();
		ReadCSV readPlayers = new ReadCSV("player.csv");
		try {
			readPlayers.next(); // skip headings
			while (true) {
				List<String> tuple = readPlayers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Player player = new Player();
				player.setID(i.next());
				player.setLocation(i.next());
				player.setHealth(Integer.parseInt(i.next()));
				player.setAttack(Integer.parseInt(i.next()));
				player.setDefense(Integer.parseInt(i.next()));
				
				playerList.add(player);
			}
			return playerList;
		} finally {
			readPlayers.close();
		}
	}	
	public static List<Pair<String, String>> getPlayerMap() throws IOException {
		List<Pair<String, String>> playerMap = new ArrayList<>();
		ReadCSV readPlayers = new ReadCSV("player_loc.csv");
		try {
			readPlayers.next(); // skip headings
			while (true) {
				List<String> tuple = readPlayers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String id = i.next();
				String loc = i.next();
				Pair<String, String> p = new Pair<>(id, loc);
				playerMap.add(p);
			}
			return playerMap;
		} finally {
			readPlayers.close();
		}
	}
	
	public static List<Pair<String, Pair<String, String>>> getConnections() throws IOException {
		List<Pair<String, Pair<String, String>>> connections = new ArrayList<>();
		ReadCSV readConnections = new ReadCSV("connections.csv");
		try {
			String o = "";
			while (true) {
				List<String> tuple = readConnections.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				String x = i.next();
				if(x.equals("room"))
				{
					o = i.next();
				}
				else
				{
					String action = x;
					String destination = i.next();
					Pair<String, String> p = new Pair<>(action, destination);
					Pair<String, Pair<String, String>> p2 = new Pair<>(o, p);
					connections.add(p2);
				}
			}
			return connections;
		} finally {
			readConnections.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		List<Pair<String, Pair<String, String>>> list = getConnections();
		for(Pair<String, Pair<String, String>> i : list) {
			String s = i.getLeft();
			String s2 = i.getRight().getLeft();
			String s3 = i.getRight().getRight();
			System.out.println("-"+s + "-" + s2 + "-" + s3);
		}
		return;
	}

}
