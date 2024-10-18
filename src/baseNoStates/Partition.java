package baseNoStates;

import java.util.ArrayList;

public class Partition extends Area {

  private final ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, Partition parentPartition) {
    super(id, parentPartition);

    //add this partition to the parent partition's (if there's one) areas list
    if (parentPartition != null) {
      parentPartition.addArea(this);
    }
  }

  public Area findAreaById(String id) {
    return super.findAreaById(id);
  }

  @Override
  public ArrayList<Space> getSpaces() {
    //create a Space array to store Space items
    ArrayList<Space> spaces = new ArrayList<>();

    //recursivily get the Space items from the current Partition and sub Partitions
    for (Area area : areas) {
      if (area instanceof Space) {                //it's a space, add it to the list
        spaces.add((Space) area);
      } else if (area instanceof Partition) {     //it's a partition, retrieve its spaces
        spaces.addAll(area.getSpaces());
      }
    }

    return spaces;
  }

  //we look for the spaces contained in the partition, then the doors contained in each space
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Space sp : getSpaces()) {
      doors.addAll(sp.getDoorsGivingAccess());
    }
    return doors;
  }

  //add the area to areas only if it isn't in it yet
  public void addArea(Area area) {
    if (!areas.contains(area)) {
      areas.add(area);
    }
  }

}
