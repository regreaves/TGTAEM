import java.util.*;
import java.io.*;

public class Item
{
  String name;
  String id;
  String location;
  String init_dscrpt;
  String invent_dscrpt;

  boolean isCritical;
  boolean canTake;
  boolean hasItem;
  boolean isHidden;
  ArrayList<Action> actions = new ArrayList<>();
  ArrayList<Action> actionsDone = new ArrayList<>();

  public Item(String name, String id, String location, String init_dscrpt, String invent_dscrpt, boolean canTake, boolean hasItem, boolean isHidden) {
    this.name = name;
    this.id = id;
    this.location = location;
    this.init_dscrpt = init_dscrpt;
    this.invent_dscrpt = invent_dscrpt;
    this.canTake = canTake;
    this.hasItem = hasItem;
    this.isHidden = isHidden;
  }

  private void getItems(String file) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String line = "";
      line = reader.readLine();   //skip headings
      while((line = reader.readLine()) != null) {
        String[] entry = line.split(",");
        int take = Integer.parseInt(entry[5]);
        int has = Integer.parseInt(entry[6]);
        int hidden = Integer.parseInt(entry[7]);

        String name = entry[0];
        String id = entry[1];
        String location = entry[2];
        String init_dscrpt = entry[3];
        String invent_dscrpt = entry[4];

        if (take == 1) {
          boolean canTake = true;
        }
        else {
          boolean canTake = false;
        }

        if (has == 1) {
          boolean hasItem = true;
        }
        else {
          boolean hasItem = false;
        }

        if (hidden == 1) {
          boolean isHidden = true;
        }
        else {
          boolean isHidden = false;
        }
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        reader.close();
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }
  }

  public String getName() {
    return name;
  }

  public String getInitDscrpt() {
    return init_dscrpt;
  }

  public void setInitDscrpt(String init_dscrpt) {
    this.init_dscrpt = init_dscrpt;
  }

  public void addInitDscrpt(String init_dscrpt) {
    this.init_dscrpt = init_dscrpt;
    return;
  }

  public String getInventDscrpt() {
    return invent_dscrpt;
  }

  public void setInventDscrpt(String invent_dscrpt) {
    this.invent_dscrpt = invent_dscrpt;
    return;
  }

  public void addInventDscrpt(String invent_dscrpt) {
    this.invent_dscrpt += invent_dscrpt;
    return;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
    return;
  }

  public ArrayList<Action> getActions() {
    //TODO
    return actions;
  }

  public void addActions(ArrayList<Action> actions) {
    int i = 0;
    Action action;
    while(i < actions.getSize()) {
      action = actions.get(i);
      this.actions.add(action);
    }
    return;
  }


  public boolean isCritical() {
    return isCritical;
  }

  public void setIsCritical(boolean isCritical) {
    this.isCritical = isCritical;
    return;
  }

  public boolean canTake() {
    return canTake;
  }

  public void setCanTake(boolean canTake) {
    this.canTake = canTake;
    return;
  }

  public boolean hasItem() {
    return hasItem;
  }

  public void setHasItem(boolean hasItem) {
    this.hasItem = hasItem;
    return;
  }

  public boolean isHidden() {
    return isHidden;
  }

  public void setIsHidden(boolean isHidden) {
    this.isHidden = isHidden;
    return;
  }

  public ArrayList<Action> getActionsDone() {
    return actionsDone;
  }

  public void addActionDone(Action actionDone) {
    this.actionsDone.add(actionDone);
    return;
  }

}
