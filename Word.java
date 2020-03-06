import java.util.*;

public class Word
{
  Bool isNoun;
  Bool isVerb;
  ArrayList synonyms;
  Int order;

  public Word(Bool isNoun, Bool isVerb, ArrayList synonyms, Int order) {
	  this.isNoun = isNoun;
    this.isVerb = isVerb;
    this.synonyms = synonyms;
    this.order = order;
  	}

  public Bool checkOrder() {
    return true;
  }

}
