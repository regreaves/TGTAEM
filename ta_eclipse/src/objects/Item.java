package objects;

import java.util.ArrayList;

import command.Action;

public class Item {
	String name;
	String id;
	String init_dscrpt;
	String invent_dscrpt;

	boolean critical;
	boolean hidden;
	boolean moved;
	boolean vowel;
	boolean plural;

	int weight;
	
	public Item() {
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean critical() {
		return critical;
	}

	public void setCritical(boolean critical) {
		this.critical = critical;
		return;
	}

	public boolean hidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		return;
	}

	public boolean moved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public void move() {
		moved = true;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int itemWeight) {
		this.weight = itemWeight;
		return;
	}

}
