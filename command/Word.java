import java.util.*;

public class Word
{
  String name;               //the word as input, can be synonym of prime
  String prime;              //the main version that the game uses to reference action/item
  int type;                 //1=verb, 2=noun, 3=direction
  boolean isVerb = false;
  boolean isNoun = false;
  boolean isDirection = false;
  boolean isPrime = false;

  public Word(String name, String prime, int type)
  {
    this.name = name;
    this.prime = prime;
    this.type = type;

    if(type == 1)
    {
      isVerb = true;
    }
    else if(type == 2)
    {
      isNoun = true;
    }
    else if(type == 3)
    {
      isDirection = true;
    }
    if(name.equals(prime))
    {
      isPrime = true;
    }
  }

  public Word getWord(String word)
  {
    if(name.equals(word))
    {
      return this;
    }
    else
    {
      return null;
    }
  }

  public String getName()
  {
    return name;
  }

  public String getPrime()
  {
    return prime;
  }

  public int getType()
  {
    return this.type;
  }

  public boolean isVerb()
  {
    return isVerb;
  }

  public boolean isNoun()
  {
    return isNoun;
  }

  public boolean isPrime()
  {
    return isPrime();
  }


}
