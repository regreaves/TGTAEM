package objects;

public class NPC extends Actor
{
	//Name of npc, used in actions
	String name;
	//Description of npc, used in examining npc
	String description;
	//ID of the initial line of dialogue for the npc
	String dialogueID;
	//The initial dialogue objects for the npc
	Dialogue dialogue;

  public NPC() {

  }

  public void setDescription(String description) {
	this.description = description;
    return;
  }

  //This does not do anything
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
}
