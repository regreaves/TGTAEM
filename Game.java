import java.util.*;

public class Game
{
  User user;
  Player player;
  Parser parser;
  Inventory items;
  NPC npcs;
  ArrayList map;
  ArrayList actionsTaken;
  ArrayList roomsVisited;
  int playerDeaths;
  int victories;
  ArrayList checkpoints;

  public Game(User user, Player player, Parser parser, Inventory items, NPC npcs, ArrayList map, ArrayList actionsTaken, ArrayList roomsVisited, int playerDeaths, int victories, ArrayList checkpoints) {
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
    return;
  }

  public void teleport(String string) {
    return;
  }

}
