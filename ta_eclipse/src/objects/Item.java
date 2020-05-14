package objects;

public class Item {
	String name; // basic name
	String id; // reference id
	String init_dscrpt; // initial description displayed
	String invent_dscrpt; // alternate description displayed in inventory or on EXAMINE

	boolean critical; // is game critical (to winning/points)
	boolean hidden; // is not visible to player
	boolean moved; // has moved from original location
	boolean vowel; // starts with vowel
	boolean plural; // is plural form
	boolean isContainer; // is am ItemContainer

	int weight; // weight

	public Item() { // POJO
	}

	@Override
	public String toString() {
		return (id + ", " + name + ", " + init_dscrpt + ", " + invent_dscrpt + ", " + critical + ", " + hidden + ", "
				+ moved + ", " + vowel + ", " + plural + ", " + isContainer + ", " + Integer.toString(weight));
	}

	public Item stringToItem(String string) {
		Item item = new Item();

		String[] stringArray = string.split(", ");

		item.setID(stringArray[0]);
		item.setName(stringArray[1]);
		item.setInitDscrpt(stringArray[2]);
		item.setInventDscrpt(stringArray[3]);

		item.setCritical(Boolean.valueOf(stringArray[4]));
		item.setHidden(Boolean.valueOf(stringArray[5]));
		item.setMoved(Boolean.valueOf(stringArray[6]));
		item.setVowel(Boolean.valueOf(stringArray[7]));
		item.setPlural(Boolean.valueOf(stringArray[8]));
		item.setIsContainer(Boolean.valueOf(stringArray[9]));

		item.setWeight(Integer.valueOf(stringArray[10]));

		return item;
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
		this.init_dscrpt += init_dscrpt;
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

	public boolean isContainer() {
		return isContainer;
	}

	public void setIsContainer(boolean isContainer) {
		this.isContainer = isContainer;
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
