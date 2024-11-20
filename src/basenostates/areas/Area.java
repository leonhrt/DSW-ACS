package basenostates.areas;

import basenostates.areas.visitor.Visitor;


/**
 * The Area class serves as the abstract component in the Composite design pattern.
 * It defines the common interface for all concrete objects (such as Partition and Space)
 * that can be part of a hierarchical structure.
 * This class allows clients to interact with both individual objects
 * and their compositions uniformly.
 */
public abstract class Area {
  protected String id;


  /**
   * Constructs an Area with the specified ID and parent partition.
   *
   * @param id The unique identifier for the area
   * @param parentPartition The partition containing this area
   */
  public Area(String id, Partition parentPartition) {
    this.id = id;
    if (parentPartition != null) {
      parentPartition.addArea(this);
    }
  }

  /**
   * Method that will accept a visitor to execute the specific method.
   *
   * @param visitor Visitor object to identify which specific code execute.
   */
  public abstract void accept(Visitor visitor);

  /**
   * Getter of the ID of the specific area, shared amongst its class implementations.
   *
   * @return The ID of the Area.
   */
  public String getId() {
    return id;
  }
}
