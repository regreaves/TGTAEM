import java.util.*;
import java.io.*;

public class Room
{
 String ID;
 String displayName;
 String description;
 ArrayList<String> connections = new ArrayList<>();
 ArrayList<Item> items = new ArrayList<>();
 ArrayList<NPC> npcs = new ArrayList<>();
 ArrayList<Action> actions = new ArrayList<>();
 boolean visited = false;

 Room(String ID, String displayName, String description, ArrayList<String> connections) {
    this.ID = ID;
    this.displayName = displayName;
    this.description = description;
    this.connections = connections;
  }

public  String getID() {
   return ID;
 }

public void setID(String ID) {
  this.ID = ID;
  return;
}

public String getDisplayName() {
  return displayName;
}

public void setDisplayName(String displayName) {
  this.displayName = displayName;
  return;
}

public String getDescription() {
  return description;
}

public void setDescription(String description) {
  this.description = description;
  return;
}

public void addDescription(String description) {
  this.description += description;
  return;
}

public ArrayList<String> getConnections() {
  return connections;
}

public void setConnections(ArrayList<String> connections) {
  this.connections = connections;
  return;
}

public ArrayList<Item> getItems() {
  return items;
}

public void addItems(ArrayList<Item> items) {
  int i = 0;
  int x = 0;
  Item item;
  Action action;
  while(i < items.getSize()) {
    item = items.get(i);
    this.items.add(item);
    item.setLocation = this.ID;
    ArrayList<Action> actions = item.getActions();
    while(x < actions.getSize()) {
      action = actions.get(x);
      this.actions.add(action);
      x++;
    }
    i++;
  }
  return;
}

public Item removeItem(Item item, Inventory inventory) {
  int x = this.items.indexOf(item);
  item.setLocation(inventory.id);
  return this.items.remove(x);
}

public ArrayList<NPC> getNPCs() {
  return npcs;
}

public void addNPC(NPC npc) {
  this.npcs.add(npc);
  return;
}

public void removeNPC(NPC npc) {
  int x = this.npcs.indexOf(npc);
  this.npcs.remove(x);
  return;
}

public ArrayList<Action> getActions() {
  return actions;
}

public void addActions(ArrayList<Item> items) {
  int i = 0;
  int x = 0;
  Item item;
  Action action;
  while(i < items.getSize()) {
    item = items.get(i);
    ArrayList<Action> actions = item.getActions();
    while(x < actions.getSize()) {
      action = actions.get(x);
      this.actions.add(action);
      x++;
    }
    i++;
  }
  return;
}

public void removeActions(ArrayList<Item> items) {
  //TODO
  int i = 0;
  int x = 0;
  Item item;
  Action action;
  while(i < items.getSize()) {
    item = items.get(i);
    ArrayList<Action> actions = item.getActions();
    while(x < actions.getSize()) {
      action = actions.get(x);
      this.actions.remove(action);
      x++;
    }
    i++;
  }
  return;
}

public boolean getVisited() {
  return visited;
}

public void setVisited(boolean visited) {
  this.visited = visited;
  return;
}

 //TODO add NPC based methods
}
