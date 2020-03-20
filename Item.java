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
  ArrayList actions = new ArrayList();
  ArrayList actionsDone = new ArrayList();

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
        String name = entry[0];
        String id = entry[1];
        String location = entry[2];
        String init_dscrpt = entry[3];
        String invent_dscrpt = entry[4];
        boolean canTake = entry[5];
        boolean hasItem = entry[6];
        boolean isHidden = entry[7];
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
    return;
  }

  public void addDescription(String description) {
    this.description += description;
    return;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
    return;
  }

  public ArrayList getActions() {
    return actions;
  }

  public void addActions(ArrayList actions) {
    //TODO
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

  public ArrayList getActionsDone() {
    return actionsDone;
  }

  public void addActionDone(Action actionDone) {
    this.actionsDone.add(actionDone);
    return;
  }

}
