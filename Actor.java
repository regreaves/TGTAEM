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
