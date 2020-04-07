package objects;

import java.util.ArrayList;

import command.Action;

public class Item {
	String name;
	String id;
	String init_dscrpt;
	String invent_dscrpt;

	boolean isCritical;
	boolean isHidden;
	boolean moved;
	boolean vowel;
	boolean plural;
	
	int itemWeight;
	
	ArrayList<Item> subItems = new ArrayList<>();
	ArrayList<Action> actions = new ArrayList<>();
	ArrayList<Action> actionsDone = new ArrayList<>();

	public Item(String id, String name, String init_dscrpt, String invent_dscrpt, boolean isHidden, boolean moved, boolean vowel, boolean plural, int itemWeight) {
		this.name = name;
		this.id = id;
		this.init_dscrpt = init_dscrpt;
		this.invent_dscrpt = invent_dscrpt;
		this.isHidden = isHidden;
		this.moved = moved;
		this.vowel = vowel;
		this.plural = plural;
		this.itemWeight = itemWeight;
	}
	
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getInitDscrpt() {
		return init_dscrpt;
	}

	public void setInitDscrpt(String init_dscrpt) {
		this.init_dscrpt = init_dscrpt;
	}

	public void addInitDscrpt(String init_dscrpt) {
		this.init_dscrpt = init_dscrpt;
		return;
	}

	public String getInventDscrpt() {
		return invent_dscrpt;
	}

	public void setInventDscrpt(String invent_dscrpt) {
		this.invent_dscrpt = invent_dscrpt;
		return;
	}

	public void addInventDscrpt(String invent_dscrpt) {
		this.invent_dscrpt += invent_dscrpt;
		return;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void addActions(ArrayList<Action> actions) {
		this.actions.addAll(actions);
		return;
	}

	public void removeAction(Action action) {
		actions.remove(action);
	}

	public boolean isCritical() {
		return isCritical;
	}

	public void setIsCritical(boolean isCritical) {
		this.isCritical = isCritical;
		return;
	}

	public void addItem(Item i) {
		subItems.add(i);
	}
	
	public void removeItem(Item i) {
		subItems.remove(i);
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
		return;
	}
	
	public boolean moved()
	{
		return moved;
	}
	
	public void move()
	{
		moved = true;
	}

	public ArrayList<Action> getActionsDone() {
		return actionsDone;
	}

	public void addActionDone(Action actionDone) {
		this.actionsDone.add(actionDone);
		return;
	}
	
	public boolean vowel() {
		return vowel;
	}
	
	public void setVowel(boolean vowel) {
		this.vowel = vowel;
		return;
	}
	
	public boolean plural() {
		return plural;
	}
	
	public void setPlural(boolean plural) {
		this.plural = plural;
		return;
	}
	
	public int getItemWeight() {
		return itemWeight;
	}
	
	public void setItemWeight(int itemWeight) {
		this.itemWeight = itemWeight;
		return;
	}

}
