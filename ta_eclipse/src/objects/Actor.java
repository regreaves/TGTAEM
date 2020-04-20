package objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Actor
{
  String location;
  String id;
  int health;
  int attack;
  int defense;
  boolean alive;

  public Actor() {
	
  }
/*	
  private void setActor(String file) {
	BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new FileReader(file));
      String line = "";
      line = reader.readLine();   //skip headings
      while((line = reader.readLine()) != null)
      {
        String[] entry = line.split(",");
		id = entry[0];
        stats.add(entry[1]);
        stats.add(entry[2]);
        stats.add(entry[3]);
		location = entry[4];
		alive = entry[5] != null;
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
*/
  
  public void setHealth(int health) {
    this.health = health;
	return;
  }
  
  public int getHealth() {
	return this.health;
  }
  
  public void setAttack(int attack) {
	this.attack = attack;
	return;
  }
	  
  public int getAttack() {
	return this.attack;
  }
  
  public void setDefense(int defense) {
	this.defense = defense;
	return;
  }
		  
  public int getDefense() {
	return this.defense;
  }
  
  public boolean checkAlive() {
    if(this.alive == true){
		return true;
	}else{
		return false;
	}
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String locationID) {
    this.location = locationID;
	return;
  }

  public String getID() {
    return id;
  }
  
  public void setID(String ID) {
	this.id = ID;
	return;
  }

  public void kill() {
	this.alive = false;
    return;
  }
  
  public void setAlive(boolean alive) {
	  this.alive = alive;
	  return;
  }
}
