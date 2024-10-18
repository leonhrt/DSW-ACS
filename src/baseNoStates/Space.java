package baseNoStates;

import java.util.ArrayList;
import java.util.List;

public class Space extends Area {

  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Partition parentPartition) {
    super(id, parentPartition);
    parentPartition.addArea(this);
  }

  public Area findAreaById(String id) {
    return super.findAreaById(id);
  }

  //return an ArrayList containing just this Space
  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<>(List.of(this));
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }

  @Override
  public String getId() {
    return id;
  }

  //add the Door if it's not on the current doors list
  public void addDoor(Door door) {
    if (!doors.contains(door)) {
      this.doors.add(door);
    }
  }

}
