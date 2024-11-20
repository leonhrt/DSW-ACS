package basenostates.areas.visitor;

import basenostates.areas.Partition;
import basenostates.areas.Space;
import basenostates.doors.Door;

/**
 * Interface that declares all the necessary methods for each type of visitor.
 */
public interface Visitor {
  /**
   * Declaration of visit partition.
   *
   * @param partition The partition to visit.
   */
  void visitPartition(Partition partition);

  /**
   * Declaration of visit space.
   *
   * @param space The space to visit
   */
  void visitSpace(Space space);

  /**
   * Declaration of visit door.
   *
   * @param door The door to visit.
   */
  void visitDoor(Door door);
}
