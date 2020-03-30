package objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Actor
{
  ArrayList<String> stats;
  String location;
  String id;
  boolean alive;

  public Actor() {
	stats = new ArrayList<>();
	location = "0";
	id = "0";
	alive = true;
  }
	
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

  public void setStats(ArrayList<String> stats) {
    this.stats = stats;
	return;
  }

  public void modifyStats(ArrayList<String> stats) {
    return;
  }

  public ArrayList<String> getStats() {
    return this.stats;
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
}
