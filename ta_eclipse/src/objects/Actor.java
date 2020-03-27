package objects;

import java.io.*;
import java.util.*;

public class Actor
{
  ArrayList<String> stats = new ArrayList<>();
  String location;
  String id;
  boolean alive;

  public Actor(String actorFile) {
	
  }
	
  private void getActor(String file) {
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

  public void modifyStats(ArrayList<?> stats) {
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

  public void kill() {
	this.alive = false;
    return;
  }
}
