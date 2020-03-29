package state;

import java.util.*;
import java.io.*;
import command.*;
import objects.*;

public class Game
{
  User user;
  Player player;
  Parser parser;
  ArrayList<Item> items = new ArrayList<>();
  ArrayList<NPC> npcs = new ArrayList<>();
  HashMap<String, Room> map = new HashMap<>();
  ArrayList<Action> actionsTaken = new ArrayList<>();
  ArrayList<String> roomsVisited = new ArrayList<>();
  int playerDeaths = 0;
  int victories = 0;
  ArrayList<Checkpoint> checkpoints = new ArrayList<>();

  //not done here yet
  public Game(User user, Player player, Parser parser)
  {
	this.user = user;
    this.player = player;
    this.parser = parser;
  }

  private void makeMap(String file) {
     BufferedReader reader = null;
     try {
       reader = new BufferedReader(new FileReader(file));
       String line = "";
       line = reader.readLine();   //skip headings
       while((line = reader.readLine()) != null) {
         String[] entry = line.split(",");
         String id = entry[0];
         String displayName = entry[1];
         String description = entry[2];
         int i = 3;
         ArrayList<String> connections = new ArrayList<>();
         while(i < 13)
         {
           connections.add(entry[i]);
           i++;
         }
         Room r = new Room(id, displayName, description, connections);
         map.put(id, r);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        reader.close();
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void makeItems(String file) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String line = "";
      line = reader.readLine();   //skip headings
      while((line = reader.readLine()) != null) {
        String[] entry = line.split(",");

        String name = entry[0];
        String id = entry[1];
        String location = entry[2];
        String init_dscrpt = entry[3];
        String invent_dscrpt = entry[4];
        int take = Integer.parseInt(entry[5]);
        int has = Integer.parseInt(entry[6]);
        int hidden = Integer.parseInt(entry[7]);
       
        boolean canTake = false;
        boolean hasItem = false;
        boolean isHidden = false;
        
        if (take == 1) {
        	canTake = true;
        }
        if (has == 1) {
          hasItem = true;
        }
        if (hidden == 1) {
          isHidden = true;
        }

        Item i = new Item(name, id, location, init_dscrpt, invent_dscrpt, canTake, hasItem, isHidden);
        items.add(i);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        reader.close();
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void makeActions(String file)
  {

  }

  public void makeNPCS(String npcFile)
  {

  }

  public void addActionTaken(Action action) {
    actionsTaken.add(action);
    return;
  }

  public void addRoomVisited(String roomID) {
    roomsVisited.add(roomID);
    return;
  }

  public void playerDies() {
    playerDeaths++;
    return;
  }

  public void playerWins() {
    victories++;
    return;
  }

  public void saveGame() {    //TODO with checkpoint
    return;
  }

  public void loadGame(Checkpoint checkpoint) {   //TODO: implement with checkpoint
    this.user = user;
    this.player = player;
    this.parser = parser;
    this.items = items;
    this.npcs = npcs;
    this.map = map;
    this.actionsTaken = actionsTaken;
    this.roomsVisited = roomsVisited;
    this.playerDeaths = playerDeaths;
    this.victories = victories;
    this.checkpoints = checkpoints;
  }

  public void teleport(String roomID) {
    player.location = roomID; //TODO: I think more is needed here
    return;
  }

}
