package objects;

import java.util.ArrayList;

import command.Action;
import objects.Room;

public class NPC extends Actor
{
	String id;
	String name;
	int health;
	int attack;
	int defense;
	String description;

  public NPC() {

  	}
/*
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
*/
  public void setID(String id) {
		this.id = id;
	    return;
  }
  
  public String getID() {
	    return this.id;
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
  
  public void setHealth(int health) {
		this.health = health;
	    return;
  }
  
  public int getHealth() {
	    return this.health;
  }
  
  public void setAttack(int attack) {
		this.attack = attack;
	    return;
  }

  public int getAttack() {
	    return this.attack;
  }
  public void setDefense(int defense) {
		this.defense = defense;
	    return;
  }

  public int getDefense() {
	    return this.defense;
  }
  
/*
  public void setFriendly(boolean friend) {
	this.friend = friend;
    return;
  }

  public ArrayList<String> getDialogue() {
    return this.dialogueBank;
  }
*/
}
