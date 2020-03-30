package objects;

import java.util.ArrayList;

import command.Action;
import objects.Room;

public class NPC extends Actor
{
  ArrayList<String> loot = new ArrayList<>();
  ArrayList<String> possibleActions = new ArrayList<>();
  ArrayList<String> availableActions = new ArrayList<>();
  String description;
  String name;
  boolean friend;
  ArrayList<String> dialogueBank = new ArrayList<>();

  public NPC(ArrayList<String> loot, ArrayList<String> possibleActions, ArrayList<String> availableActions, String description, String name, boolean friend, ArrayList<String> dialogueBank) {
	this.loot = loot;
    this.possibleActions = possibleActions;
    this.availableActions = availableActions;
    this.description = description;
    this.name = name;
    this.friend = friend;
    this.dialogueBank = dialogueBank;
  	}

  public void addActions(ArrayList<String> actions) {
   int x = 0;
   boolean canDo = false;
   while(x < actions.size())
   {
     canDo = Action.checkCondition(actions.get(x));
     if(canDo)
     {
       availableActions.add(actions.get(x));
     }
     else
     {
       possibleActions.add(actions.get(x));
     }
     x+=1;
   }
  }

  public void checkActions() {
   int x = 0;
   boolean canDo = false;
   while(x < possibleActions.size())
   {
     canDo = Action.checkCondition(possibleActions.get(x));
     if(canDo)
     {
       availableActions.add(possibleActions.get(x));
     }
     x+=1;
   }

   x = 0;
   while(x < availableActions.size())
   {
     canDo = Action.checkCondition(availableActions.get(x));
     if(!canDo)
     {
       possibleActions.add(availableActions.get(x));
     }
     x+=1;
   }
  }

  public ArrayList<String> getAvailableActions() {
    return this.availableActions;
  }

  public ArrayList<String> getPossibleActions() {
    return this.possibleActions;
  }

  public void addLoot(Item item) {
	loot.add(item.toString());
    return;
  }

  public void dropLoot(Item item) {
	loot.remove(item.toString());
	//Will fix later
	//room = new Room(room.getID(), room.getDisplayName(), room.getDescription(), room.getConnections());
	//room.addItem(item);
    return;
  }

  public void setDescription(String description) {
	this.description = description;
    return;
  }

  public void addDescription(String description) {
    return;
  }

  public String getDescription() {
    return this.description;
  }

  public void setName(String name) {
	this.name = name;
    return;
  }

  public String getName() {
    return this.name;
  }

  public void setFriendly(boolean friend) {
	this.friend = friend;
    return;
  }

  public ArrayList<String> getDialogue() {
    return this.dialogueBank;
  }

}
