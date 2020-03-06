import java.util.*;

public class Actor
{
  List stats;
  String location;
  String id;
  Bool alive;

  public Actor(List stats, String location, String id, Bool alive) {
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
    return
  }

  public Bool checkAlive() {
    return true;
  }

  public String getLocation() {
    return "empty";
  }

}
