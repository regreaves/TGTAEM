import java.util.*;

public class Actor
{
  List stats;
  String location;
  String id;
  boolean alive;

  public Actor(List stats, String location, String id, boolean alive) {
	  this.stats = stats;
	  this.location = location;
    this.id = id;
    this.alive = alive;
  	}

  public void setStats(List stats) {
    return;
  }

  public void modifyStats(List stats) {
    return;
  }

  public void getStats() {
    return;
  }

  public boolean checkAlive() {
    return true;
  }

  public String getLocation() {
    return "empty";
  }

  public void setLocation(String locationID) {
    return;
  }

  public String getID() {
    return "empty";
  }

  public void kill() {
    return;
  }
}
