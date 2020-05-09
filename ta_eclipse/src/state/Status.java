package state;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import objects.Item;

public class Status {
	int move;
	int waterOn;

	ArrayList<Item> equipped = new ArrayList<>();

	boolean hiding;
	boolean monsterCheck1;
	boolean sitting;
	boolean laptop;
	boolean window;
	boolean flashlight;
	boolean sink;
	boolean shower;
	boolean clothes;
	boolean wet;
	boolean TV;
	boolean searchCouch;
	boolean PC;
	boolean mailFlag;
	boolean searchGrass1;
	boolean searchGrass2;
	boolean flood;
	boolean dialogue;

	public Status() {
		clothes = true;
		TV = true;
	}

	public boolean advance() {
		move++;
		if (sink && shower) {
			waterOn++;
		} else {
			waterOn = 0;
		}
		if (waterOn > 10) {
			flood = true;
		}
		return true;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public int getWaterOn() {
		return waterOn;
	}

	public void setWaterOn(int waterOn) {
		this.waterOn = waterOn;
	}

	public ArrayList<Item> getEquipped() {
		return equipped;
	}

	public void setEquipped(ArrayList<Item> equipped) {
		this.equipped = equipped;
	}
	
	public void equip(Item i) {
		this.equipped.add(i);
	}

	public boolean isHiding() {
		return hiding;
	}

	public void setHiding(boolean hiding) {
		this.hiding = hiding;
	}

	public boolean isMonsterCheck1() {
		return monsterCheck1;
	}

	public void setMonsterCheck1(boolean monsterCheck1) {
		this.monsterCheck1 = monsterCheck1;
	}

	public boolean isSitting() {
		return sitting;
	}

	public void setSitting(boolean sitting) {
		this.sitting = sitting;
	}

	public boolean isLaptop() {
		return laptop;
	}

	public void setLaptop(boolean laptop) {
		this.laptop = laptop;
	}

	public boolean isWindow() {
		return window;
	}

	public void setWindow(boolean window) {
		this.window = window;
	}

	public boolean isFlashlight() {
		return flashlight;
	}

	public void setFlashlight(boolean flashlight) {
		this.flashlight = flashlight;
	}

	public boolean isSink() {
		return sink;
	}

	public void setSink(boolean sink) {
		this.sink = sink;
	}

	public boolean isShower() {
		return shower;
	}

	public void setShower(boolean shower) {
		this.shower = shower;
	}

	public boolean isClothes() {
		return clothes;
	}

	public void setClothes(boolean clothes) {
		this.clothes = clothes;
	}

	public boolean isWet() {
		return wet;
	}

	public void setWet(boolean wet) {
		this.wet = wet;
	}

	public boolean isTV() {
		return TV;
	}

	public void setTV(boolean tV) {
		TV = tV;
	}

	public boolean isSearchCouch() {
		return searchCouch;
	}

	public void setSearchCouch(boolean searchCouch) {
		this.searchCouch = searchCouch;
	}

	public boolean isPC() {
		return PC;
	}

	public void setPC(boolean pC) {
		PC = pC;
	}

	public boolean isMailFlag() {
		return mailFlag;
	}

	public void setMailFlag(boolean mailFlag) {
		this.mailFlag = mailFlag;
	}

	public boolean isSearchGrass1() {
		return searchGrass1;
	}

	public void setSearchGrass1(boolean searchGrass1) {
		this.searchGrass1 = searchGrass1;
	}

	public boolean isSearchGrass2() {
		return searchGrass2;
	}

	public void setSearchGrass2(boolean searchGrass2) {
		this.searchGrass2 = searchGrass2;
	}

	public boolean isFlood() {
		return flood;
	}

	public void setFlood(boolean flood) {
		this.flood = flood;
	}
	
	public boolean isDialogue() {
		return dialogue;
	}

	public void setDialogue(boolean dialogue) {
		this.dialogue = dialogue;
	}

	public String toJSON() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(this);
	}
	
	public Status fromJSON(String s) throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.readValue(s, Status.class);
	}
	
}
