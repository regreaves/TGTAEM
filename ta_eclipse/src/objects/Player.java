package objects;

import java.util.*;

public class Player
{
  Inventory inventory;

  public Player()
  {
	  this.inventory = new Inventory(0, null, null);
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
