package objects;

import java.util.*;

public class Player
{
  Inventory inventory;
  public String location;

  public Player()
  {
	  this.inventory = new Inventory(0, null, null);
	  this.location = "0";
  }

  public void changeStats()
  {
    return;
  }

  public void checkInventory()
  {
    return;
  }

  public Inventory getInventory()
  {
	return inventory;
  }
}
