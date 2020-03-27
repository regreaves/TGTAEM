import java.util.*;

public class NPC
{
  ArrayList loot;
  ArrayList possibleActions;
  ArrayList availableActions;
  String description;
  String name;
  boolean friend;
  ArrayList dialogueBank;

  public NPC(ArrayList loot, ArrayList possibleActions, ArrayList availableActions, String description, String name, boolean friend, ArrayList dialogueBank) {
	this.loot = loot;
    this.possibleActions = possibleActions;
    this.availableActions = availableActions;
    this.description = description;
    this.name = name;
    this.friend = friend;
    this.dialogueBank = dialogueBank;
  	}

  public void addActions(ArrayList actions) {
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

  public ArrayList getAvailableActions() {
    return this.availableActions;
  }

  public ArrayList getPossibleActions() {
    return this.possibleActions;
  }

  public void addLoot(Item item) {
    return;
  }

  public void dropLoot() {
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

  public ArrayList getDialogue() {
    return this.dialogueBank;
  }

}
