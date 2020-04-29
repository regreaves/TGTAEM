package state;

import java.util.ArrayList;

import objects.Item;

public class Status {
	public int move;
	public int waterOn;
	
	public ArrayList<Item> equipped = new ArrayList<>();
	
	public boolean hiding;
	public boolean monsterCheck1;
	public boolean sitting;
	public boolean laptop;
	public boolean window;
	public boolean flashlight;
	public boolean sink;
	public boolean shower;
	public boolean clothes;
	public boolean wet;
	public boolean TV;
	public boolean searchCouch;
	public boolean PC;
	public boolean mailFlag;
	public boolean searchGrass1;
	public boolean searchGrass2;
	public boolean flood;
	
	

	public Status() {
		move = 0;
		waterOn = 0;
		hiding = false;
		monsterCheck1 = false;
		sitting = false;
		laptop = false;
		window = false;
		flashlight = false;
		sink = false;
		shower = false;
		clothes = true;
		wet = false;
		TV = true;
		searchCouch = false;
		PC = false;
		mailFlag = false;
		searchGrass1 = false;
		searchGrass2 = false;
		flood = false;
	}
	
	public boolean advance() {
		move++;
		if(sink && shower) {
			waterOn++;
		} else {
			waterOn = 0;
		}
		if(waterOn > 10) {
			flood = true;
		}
		return true;
	}
	
	
	
	

}

