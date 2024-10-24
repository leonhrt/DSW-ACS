package baseNoStates;

import java.util.ArrayList;


/**
 * The Area class represents an abstract area within a structure,
 * which can contain partitions, spaces and doors. It serves as a base class for specific area types.
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
