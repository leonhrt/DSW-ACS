package baseNoStates.areas;

import baseNoStates.areas.visitor.Visitor;


/**
 * The Area class serves as the abstract component in the Composite design pattern.
 * It defines the common interface for all concrete objects (such as Partition and Space)
 * that can be part of a hierarchical structure.
 *
 * This class allows clients to interact with both individual objects and their compositions uniformly.
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
    if (parentPartition != null) parentPartition.addArea(this);
  }

  public abstract void accept(Visitor visitor);

  public String getId() {
    return id;
  }
}
