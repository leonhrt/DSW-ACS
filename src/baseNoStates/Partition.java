package baseNoStates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

  //we look for the spaces contained in the partition,
  //then the doors contained in each space,
  //then we keep the ones that give access from outside the area
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    //get all spaces of the partition
    ArrayList<Space> spaces = getSpaces();
    //convert to a set to be more efficient when looking for a space in the list
    Set<Space> spaceSet = new HashSet<>(spaces);

    ArrayList<Door> doors = new ArrayList<>();
    for (Space sp : spaces) {
      for(Door door : sp.getDoorsGivingAccess()){
        if(!(spaceSet.contains(door.getFromSpace()) && spaceSet.contains(door.getToSpace()))){
          doors.add(door);
        }
      }
    }
    return doors;

    /*
    //old implementation -> all doors inside the area are given, therefore all are closed, but exterior can be declared (in DirectoryAreas) as a space of building instead of null parentPartition
    ArrayList<Door> doors = new ArrayList<>();
    for (Space sp : getSpaces()) {
      doors.addAll(sp.getDoorsGivingAccess());
    }
    return doors;
     */
  }

  //add the area to areas only if it isn't in it yet
  public void addArea(Area area) {
    if (!areas.contains(area)) {
      areas.add(area);
    }
  }

}
