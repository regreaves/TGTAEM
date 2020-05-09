package command;

public class Word {
	String name; // the word as input, can be synonym of prime
	String prime; // the main version that the game uses to reference action/item
	int type; // 1=verb, 2=noun
	boolean isVerb = false;
	boolean isNoun = false;
	boolean isPrime = false;

	public Word(String name, String prime, int type) { // constructor
		this.name = name;
		this.prime = prime;
		this.type = type;

		if (type == 1) {
			isVerb = true;
		} else if (type == 2) {
			isNoun = true;
		}
		if (name.equals(prime)) {
			isPrime = true;
		}
	}

	// Dummy constructor for making word objects when reading in from actions.csv
	public static Word makeWord(String word, int type) {
		Word newWord = new Word(word, word, type);
		return newWord;
	}

	public String getName() {
		return name;
	}

	public String getPrime() {
		return prime;
	}

	public int getType() {
		return this.type;
	}

	public boolean isVerb() {
		return isVerb;
	}

	public boolean isNoun() {
		return isNoun;
	}

	public boolean isPrime() {
		return isPrime;
	}

}
