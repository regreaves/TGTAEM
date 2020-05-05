package objects;

import java.util.ArrayList;

import command.Action;
import objects.Room;

public class NPC extends Actor
{
	String name;
	String description;
	String dialogueID;
	Dialogue dialogue;

  public NPC() {

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
  
  public void setDialogueID(String dialogueID) {
	this.dialogueID = dialogueID;
    return;
  }

  public String getDialogueID() {
    return this.dialogueID;
  }
  
  public void setDialogue(Dialogue dialogue) {
		this.dialogue = dialogue;
	    return;
	  }

	  public Dialogue getDialogue() {
	    return this.dialogue;
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
