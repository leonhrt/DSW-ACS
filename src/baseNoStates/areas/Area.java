package baseNoStates.areas;

import baseNoStates.doors.Door;

import java.util.ArrayList;



/**
 * The Area class serves as the abstract component in the Composite design pattern.
 * It defines the common interface for all concrete objects (such as Partition and Space)
 * that can be part of a hierarchical structure.
 *
 * This class allows clients to interact with both individual objects and their compositions uniformly.
 */
public abstract class Area {
  protected String id;
  protected Partition parentPartition;


  /**
   * Constructs an Area with the specified ID and parent partition.
   *
   * @param id the unique identifier for the area
   * @param parentPartition the partition containing this area
   */
  public Area(String id, Partition parentPartition) {
    this.id = id;
    this.parentPartition = parentPartition;
  }

  public abstract void accept(Visitor visitor);

  /**
   * Finds an area by its ID.
   *
   * @param id the ID of the area to find
   * @return the area with the specified ID, or null if not found
   */
  public Area findAreaById(String id) {
    return DirectoryAreas.findAreaById(id);
  }

  public String getId() {
    return id;
  }

  public abstract ArrayList<Space> getSpaces();

  public abstract ArrayList<Door> getDoorsGivingAccess();

}
