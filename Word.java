import java.util.*;

public class Word
{
  String name;
  String prime;
  int type;
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

  public String getWord()
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
