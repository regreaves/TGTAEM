import java.util.*;

public class Item
{
  String name;
  String description;
  String location;
  ArrayList availableActions = new ArrayList();
  ArrayList possibleActions = new ArrayList();
  boolean isCritical;
  boolean isCollectable;
  boolean hasItem;
  boolean isHidden;
  ArrayList actionsDone = new ArrayList();

  public Item(String name, String description, String location, ArrayList actions, boolean isCritical, boolean isCollectable, boolean hasItem, boolean isHidden, ArrayList actionsDone) {
    this.name = name;
    this.description = description;
    this.location = location;
    this.isCritical = isCritical;
    this.isCollectable = isCollectable;
    this.hasItem = hasItem;
    this.isHidden = isHidden;
    this.actionsDone = actionsDone;
    int x = 0;
    boolean canDo = false;
    while(x < actions.size())
    {
      canDo = Action.checkCondition(actions.get(x));
      if(canDo)
      {
        availableActions.add(actions.get(x));
      }
      else
      {
        possibleActions.add(actions.get(x));
      }
      x+=1;
    }
  }

  public String getName() {
    return name;
  }

  public setName(String name) {
    this.name = name;
    return;
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

  public ArrayList getAllActions() {
    ArrayList actions = new ArrayList();
    int x = 0;
    while(x < availableActions.size())
    {
      actions.add(availableActions.get(x));
      x += 1;
    }
    x = 0;
    while(x < possibleActions.size())
    {
      actions.add(possibleActions.get(x));
      x += 1;
    }
    return actions;
  }

  public void setAllActions(ArrayList actions) {
    //TODO
    return;
  }

  public ArrayList getAvailableActions()  {
    checkActions();
    return availableActions;
  }

  public void setAvailableActions(ArrayList availableActions) {
    this.availableActions = availableActions;
    return;
  }

  public ArrayList getPossibleActions()  {
    checkActions();
    return possibleActions;
  }

  public void setPossibleActions(ArrayList possibleActions) {
    this.possibleActions = possibleActions;
    return;
  }

  public void addActions(ArrayList actions) {
    //TODO
    return;
  }

  public void checkActions() {
    int x = 0;
    boolean canDo = false;
    while(x < possibleActions.size())
    {
      canDo = Action.checkCondition(possibleActions.get(x));
      if(canDo)
      {
        availableActions.add(possibleActions.get(x));
      }
      x += 1;
    }

    x = 0;
    while(x < availableActions.size())
    {
      canDo = Action.checkCondition(availableActions.get(x));
      if(!canDo)
      {
        possibleActions.add(availableActions.get(x));
      }
      x += 1;
    }
  }

  public boolean IsCritical() {
    return isCritical;
  }

  public void setIsCritical(boolean isCritical) {
    this.isCritical = isCritical;
    return;
  }

  public boolean IsCollectable() {
    return isCollectable;
  }

  public void setIsCollectable(boolean isCollectable) {
    this.isCollectable = isCollectable;
    return;
  }

  public boolean getHasItem() {
    return hasItem;
  }

  public void setHasItem(boolean hasItem) {
    this.hasItem = hasItem;
    return;
  }

  public boolean getIsHidden() {
    return isHidden;
  }

  public void setIsHidden(boolean isHidden) {
    this.isHidden = isHidden;
    return;
  }

  public ArrayList getActionsDone() {
    return actionsDone;
  }

  public setActionsDone(ArrayList actionsDone) {
    this.actionsDone = actionsDone;
    return;
  }

  public void addActionsDone(ArrayList actionsDone) {
    //TODO
    return;
  }

}
