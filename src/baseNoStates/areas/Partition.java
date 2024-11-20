package baseNoStates.areas;

import baseNoStates.areas.visitor.Visitor;
import baseNoStates.doors.Door;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * The Partition class represents a composite object in the Composite design pattern.
 * It can contain other Area objects, such as Space instances or other Partition instances.
 *
 * This allows for the creation of a tree structure where partitions can contain multiple areas,
 * enabling hierarchical organization and manipulation of the entire structure.
 */
public class Partition extends Area {

  private final ArrayList<Area> areas = new ArrayList<>();

  /**
   * Constructs a Partition with the specified ID and parent partition.
   * This constructor adds the partition to its parent's area list, if it has one.
   *
   * @param id the unique identifier for the partition
   * @param parentPartition the parent partition containing this partition, or null if it doesn't have one.
   */
  public Partition(String id, Partition parentPartition) {
    super(id, parentPartition);

    if (parentPartition != null) {
      parentPartition.addArea(this);
    }
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitPartition(this);
  }

  public ArrayList<Area> getAreas() {
    return areas;
  }

  /**
   * Finds an area by its ID using the Area superclass.
   *
   * @param id the ID of the area to find
   * @return the area with the specified ID, or null if not found
   */
  public Area findAreaById(String id) {
    return super.findAreaById(id);
  }

  /**
   * Retrieves a list of spaces contained within this partition and its sub-partitions.
   *
   * @return an ArrayList of spaces in this partition
   */
  @Override
  public ArrayList<Space> getSpaces() {
    // create a Space array to store Space items
    ArrayList<Space> spaces = new ArrayList<>();

    // recursively get the Space items from the current Partition and sub Partitions
    for (Area area : areas) {
      if (area instanceof Space) {                //it's a space, add it to the list
        spaces.add((Space) area);
      } else if (area instanceof Partition) {     //it's a partition, retrieve its spaces
        spaces.addAll(area.getSpaces());
      }
    }

    return spaces;
  }


  /**
   * Retrieves a list of doors that provide access from outside this partition.
   * This method checks for doors in spaces contained in this partition
   * and excludes doors that connect spaces both contained within the partition.
   *
   * @return an ArrayList of doors giving access to this partition from outside
   */
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    // get all spaces of the partition
    ArrayList<Space> spaces = getSpaces();
    // convert to a set to be more efficient when looking for a space in the list
    Set<Space> spaceSet = new HashSet<>(spaces);

    ArrayList<Door> doors = new ArrayList<>();
    // Loops through all spaces contained in this partition and their doors,
    // then selects doors that don't connect spaces both contained within the partition.
    for (Space sp : spaces) {
      for(Door door : sp.getDoorsGivingAccess()){
        if(!(spaceSet.contains(door.getFromSpace()) && spaceSet.contains(door.getToSpace()))){
          doors.add(door);
        }
      }
    }
    return doors;

    /*
    // old implementation -> all doors inside the area are given, therefore all are closed, but exterior can be declared (in DirectoryAreas) as a space of building instead of null parentPartition
    ArrayList<Door> doors = new ArrayList<>();
    for (Space sp : getSpaces()) {
      doors.addAll(sp.getDoorsGivingAccess());
    }
    return doors;
     */
  }

  /**
   * Adds an Area to this Partition's collection of areas,
   * ensuring it is not added more than once.
   * This method allows for the dynamic addition of areas to the composite structure.
   *
   * @param area the Area to be added
   */
  public void addArea(Area area) {
    if (!areas.contains(area)) {
      areas.add(area);
    }
  }

}
