import java.util.*;
import java.io.*;

public class Parser
{
  ArrayList<Word> verbBank = new ArrayList<>();
  ArrayList<Word> nounBank = new ArrayList<>();
  ArrayList<Word> directionBank = new ArrayList<>();
  ArrayList<Action> actionBank = new ArrayList<>();

  public Parser(String wordFile, String actionFile) {

  	}

  private void getWords(String file)
  {
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new FileReader(file));
      String line = "";
      line = reader.readLine();   //skip headings
      while((line = reader.readLine()) != null)
      {
        String[] entry = line.split(",");
        int type = Integer.parseInt(entry[2]);
        if(type == 1)
        {
          verbBank.add(Word(entry[0], entry[1], type));
        }
        else if(type == 2)
        {
          nounBank.add(Word(entry[0], entry[1], type));
        }
        else if(type == 3)
        {
          directionBank.add(Word(entry[0], entry[1], type));
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        reader.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }

    }
  }

  private void getActions(String file)
  {
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new FileReader(file));
      String line = "";
      line = reader.readLine();   //skip headings
      while((line = reader.readLine()) != null)
      {
        String[] entry = line.split(",");
        String name = entry[0];
        String verb = entry[1];
        String noun = entry[2];
        int method = Integer.parseInt(entry[3]);
        Word v = makeWord(verb, 1);
        Word n = makeWord(noun, 2);
        if(v == null || n == null)
        {
          System.out.println("Word not in bank");
        }
        actionBank.add(Action(name, v, n, method));
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        reader.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }

    }
  }

  private Word makeWord(String word, int type)
  {
    ArrayList bank = null;
    if(type == 1)
    {
      bank = this.verbBank;
    }
    else if(type == 2)
    {
      bank = this.nounBank;
    }
    else if(type == 3)
    {
      bank = this.directionBank;
    }
    int i = 0;
    while(i < bank.size())
    {
      Word w = bank.get(i);
      if((w.getName()).equals(word))
      {
        return Word.getWord(word);
      }
      i++;
    }
    return null;
  }

  public Action getAction(String input)
  {
    Action action = null;
    String[] words = input.split(" ");
    if(words.length != 2)
    {
      if(words.length == 1)   //only accepting directions as short forms
      {
        Word d = makeWord(words[0], 3);
        action = Action.going(d.getPrime());
      }
      else
      {
        //TODO
      }
    }
    Word v = makeWord(words[0], 1);
    int type = 2;
    if((v.getPrime()).equals("go"))
    {
      type = 3;
    }
    Word n = makeWord(words[1], type);
    if(v == null || n == null)
    {
      System.out.println("Not a valid action");
    }
    String name = v.getPrime() + " " + n.getPrime();
    if(checkAction(name))
    {
      action = Action.getAction(name);
    }

    return action;
  }

  private boolean checkAction(String name)
  {
    int i = 0;
    while(i < actionBank.size())
    {
      Action a = actionBank.get(i);
      if(a.getName().equals(name))
      {
        return true;
      }
      i++;
    }
    return false;
  }

}
