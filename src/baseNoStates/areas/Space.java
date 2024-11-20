package baseNoStates.areas;

import baseNoStates.doors.Door;

import java.util.ArrayList;
import java.util.List;

/**
 * The Space class represents a leaf object in the Composite design pattern.
 * It does not contain any child objects, but it implements the common interface
 * defined by the Area class, allowing clients to treat it uniformly.
 *
 * Spaces can contain doors and provide access to other areas.
 */
public class Space extends Area {

  private final ArrayList<Door> doors = new ArrayList<>();

  /**
   * Constructs a Space with the specified ID and parent partition.
   * This constructor adds the space to its parent's area list, if it does have one.
   *
   * @param id the unique identifier for the space
   * @param parentPartition the partition containing this space
   */
  public Space(String id, Partition parentPartition) {
    super(id, parentPartition);

    if(parentPartition!=null) parentPartition.addArea(this);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visitSpace(this);
  }

  /**
   * Finds an area by its ID using the Area superclass method.
   *
   * @param id the ID of the area to find
   * @return the area with the specified ID, or null if not found
   */
  public Area findAreaById(String id) {
    return super.findAreaById(id);
  }

  /**
   * Retrieves a list containing only this space.
   *
   * @return an ArrayList containing this space
   */
  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<>(List.of(this));
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }

  /**
   * Adds a door to this space's list of doors,
   * ensuring it is not added more than once.
   *
   * @param door the door to be added to this space
   */
  public void addDoor(Door door) {
    if (!doors.contains(door)) {
      doors.add(door);
      DirectoryAreas.addDoor(door);
    }
  }

}
