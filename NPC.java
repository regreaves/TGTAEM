import java.util.*;

public class NPC
{
  ArrayList loot;
  ArrayList possibleActions;
  ArrayList availableActions;
  String description;
  String name;
  Bool friend;
  ArrayList dialogueBank;

  public NPC(ArrayList loot, ArrayList possibleActions, ArrayList availableActions, String description, String name, Bool friend, ArrayList dialogueBank) {
	  this.loot = loot;
    this.possibleActions = possibleActions;
    this.availableActions = availableActions;
    this.description = description;
    this.name = name;
    this.friend = friend;
    this.dialogueBank = dialogueBank;
  	}

  public void addActions(ArrayList actions) {
    return;
  }

  public void checkActions() {
    return;
  }

  public ArrayList getAvailableActions() {
    return availableActions;
  }

  public ArrayList getPossibleActions() {
    return possibleActions;
  }

  public void addLoot(Item item) {
    return;
  }

  public void dropLoot() {
    return;
  }

  public void setDescription(String description) {
    return;
  }

  public void addDescription(String description) {
    return;
  }

  public String getDescription() {
    return "empty";
  }

  public void setName(String name) {
    return;
  }

  public String getName() {
    return "empty";
  }

  public void setFriendly(boolean friend) {
    return;
  }

  public ArrayList getDialogue() {
    return dialogueBank;
  }

}
