import java.util.*;

public class Game
{
  User user;
  Player player;
  Parser parser;
  Inventory items;
  ArrayList npcs = new ArrayList();
  ArrayList map;
  ArrayList actionsTaken = new ArrayList();
  ArrayList roomsVisited = new ArrayList();
  int playerDeaths = 0;
  int victories = 0;
  ArrayList checkpoints;

  //not done here yet
  public Game(User user, Player player, Parser parser)
  {
	  this.user = user;
  }

  public void makeMap(String map) {
    return;
  }

  public void addRooms(String rooms) {
    return;
  }

  public void addActionTaken(Action action) {
    return;
  }

  public void addRoomVisited(String room) {
    return;
  }

  public void playerDies() {
    return;
  }

  public void addVictory() {
    return;
  }

  public void saveGame() {
    return;
  }

  public void loadGame(String game) {
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

  public void teleport(String string) {
    return;
  }

}
