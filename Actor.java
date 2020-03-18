import java.util.*;

public class Actor
{
  List stats;
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
        stats.add(entry[0], entry[1], entry[2]);
		String location = entry[3];
		String id = entry[4];
		boolean = alive[5];
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

  public void setStats(List stats) {
    this.stats = stats;
	return;
  }

  public void modifyStats(List stats) {
    return;
  }

  public void getStats() {
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
