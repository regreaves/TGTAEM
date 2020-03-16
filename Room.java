import java.util.*;

public class Room
{
 String ID;
 String displayName;
 String description;
 ArrayList connections = new ArrayList();
 ArrayList items = new ArrayList();
 ArrayList npcs = new ArrayList();
 ArrayList availableActions = new ArrayList();
 ArrayList possibleActions = new ArrayList();
 boolean visited;

 Room(String ID, String displayName, String description, ArrayList connections, ArrayList items, ArrayList npcs, ArrayList actions, boolean visited) {
   this.ID = ID;
   this.displayName = displayName;
   this.description = description;
   this.connections = connections;
   this.items = items;
   this.npcs = npcs;
   this.visited = visited;
   int x = 0;
   boolean canDo = false;
   while(x < actions.size()) {
     canDo = Action.checkCondition(actions.get(x));
     if(canDo) {
       availableActions.add(actions.get(x));
     }
     else {
       possibleActions.add(actions.get(x));
     }
     x+=1;
   }
 }

public  String getID() {
   return ID;
 }

public void setID(String ID) {
  this.ID = ID;
  return;
}

public String getDisplayName() {
  return displayName;
}

public void setDisplayName(String displayName) {
  this.displayName = displayName;
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

public ArrayList getConnections() {
  return connections;
}

public void setConnections(ArrayList connections) {
  this.connections = connections;
  return;
}

public ArrayList getItems() {
  return items;
}

public void addItems(ArrayList items) {
  this.items = items;
  ArrayList actions = item.getAllActions();
  addActions(actions);
}

public void removeItems(ArrayList items) {
  //TODO
  int x = 0;
  while(x < items.size()) {
    Item remove = items.get(x);
    this.items.remove(remove);
    //need to add each removed item to the player's inventory
    x += 1;
  }
  return;
}

public ArrayList getNPCs() {
  return npcs;
}

public void addNPC(NPC npc) {
  this.npcs.add(npc);
  return;
}

public void removeNPC(NPC npc) {
  int x = this.npcs.indexOf(npc);
  this.npcs.remove(x);
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

public ArrayList getAvailableActions() {
  return availableActions;
}

public void setAvailableActions(ArrayList availableActions) {
  this.availableActions = availableActions;
  return;
}

public ArrayList getPossibleActions() {
  return possibleActions;
}

public void setPossibleActions(ArrayList possibleActions) {
  this.possibleActions = possibleActions;
  return;
}

public void addActions(ArrayList actions) {
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
     x+=1;
   }

   x = 0;
   while(x < availableActions.size())
   {
     canDo = Action.checkCondition(availableActions.get(x));
     if(!canDo)
     {
       possibleActions.add(availableActions.get(x));
     }
     x+=1;
   }
 }

public boolean getVisited() {
  return visited;
}

public void setVisited(boolean visited) {
  this.visited = visited;
  return;
}

 //TODO add NPC based methods
}
