package basenostates.areas.visitor;

import basenostates.areas.Area;
import basenostates.areas.DirectoryAreas;
import basenostates.areas.Partition;
import basenostates.areas.Space;
import basenostates.doors.Door;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This visitor class accesses to the root area and look in all its tree
 * for the specified door until it finds it,
 * or returns null if it doesn't exist.
 */
public class FindDoorByIdVisitor implements Visitor {
  private Door door;
  private final String doorId;

  private static final Logger milestone2 = LoggerFactory.getLogger("secondMilestone");

  private FindDoorByIdVisitor(String id) {
    this.doorId = id;
    this.door = null;
  }

  /**
   * This static methods ensures that a single instance of the visitor
   * is not executed more than once, by creating an instance
   * and returning the desired value at the same method.
   * It starts the recursive iteration to find the door by the id from the root area of the tree.
   *
   * @param id The id of the door to retrieve.
   * @return The door corresponding to the id.
   */
  public static Door findDoorById(String id) {
    milestone2.debug("Starting visitor findDoorById for the id: " + id);
    FindDoorByIdVisitor visitor = new FindDoorByIdVisitor(id);
    Area root = DirectoryAreas.getRootArea();
    root.accept(visitor);
    milestone2.debug("findDoorById visitor found the door " + visitor.door);
    return visitor.door;
  }

  /**
   * A partition can contain other partition or spaces,
   * so it iterates recursively its sub areas until the corresponding door is found.
   *
   * @param partition The partition to check its id.
   */
  @Override
  public void visitPartition(Partition partition) {
    milestone2.debug("findDoorById visiting partition: " + partition);
    int i = 0;
    ArrayList<Area> areas = partition.getAreas();
    while (door == null && i < areas.size()) {
      areas.get(i).accept(this);
      i++;

    }
  }

  /**
   * A space can contain multiple doors, so it iterates on them to find the corresponding one.
   *
   * @param space The space to check its id.
   */
  @Override
  public void visitSpace(Space space) {
    milestone2.debug("findDoorById visiting space: " + space);
    int i = 0;
    ArrayList<Door> doors = space.getDoors();
    while (door == null && i < doors.size()) {
      doors.get(i).accept(this);
      i++;
    }
  }

  /**
   * If the door corresponds to the specified id,
   * the attribute door is modified and recursive iteration stops, else do nothing.
   *
   * @param door The door to modify.
   */
  @Override
  public void visitDoor(Door door) {
    milestone2.debug("getDoorsGivingAccess visiting door: " + door);
    if (door.getId().equals(doorId)) {
      this.door = door;
    }
  }
}
