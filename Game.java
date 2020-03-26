import java.util.*;
import java.io.*;

public class Game
{
  User user;
  Player player;
  Parser parser;
  Inventory items;
  ArrayList<NPC> npcs = new ArrayList<>();
  Map map;
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

  public void makeMap(String mapFile)
  {
    return;
  }

  private void getRooms(String file) {
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
         while(i < 13)
         {
           connections.add(entry[i]);
           i++;
         }
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
  public void makeItems(String itemFile)
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
