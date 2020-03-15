import java.util.*;

public class Word
{
  boolean isNoun;
  boolean isVerb;
  ArrayList synonyms;
  int order;

  public Word(boolean isNoun, boolean isVerb, ArrayList synonyms, int order) {
	this.isNoun = isNoun;
    this.isVerb = isVerb;
    this.synonyms = synonyms;
    this.order = order;
  	}

  public boolean checkOrder() {
    return true;
  }
  
  public int getOrder() {
	return 0;
  }
  
  public ArrayList getSynonyms() {
	return;
  }
  
  public void addSynonyms(String word, String syn) {
	return;
  }
  
  public void setIsNoun(boolean noun) {
	return;
  }
  
  public boolean isNoun() {
	return true;
  }
  
  public void setIsVerb(boolean verb) {
	return;
  }
  
  public boolean isVerb() {
	return true;
  }

}
