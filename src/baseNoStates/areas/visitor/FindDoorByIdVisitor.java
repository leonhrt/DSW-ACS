package baseNoStates.areas.visitor;

import baseNoStates.areas.Area;
import baseNoStates.areas.DirectoryAreas;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import baseNoStates.doors.Door;

import java.util.ArrayList;

/**
 * This visitor class accesses to the root area and look in all its tree for the specified door until it finds it,
 * or returns null if it doesn't exist.
 */
public class FindDoorByIdVisitor implements Visitor {
  private Door door;
  private final String doorId;

  private FindDoorByIdVisitor(String id) {
    this.doorId = id;
    this.door = null;
  }

  /**
   * This static methods ensures that a single instance of the visitor is not executed more than once, by creating an
   * instance and returning the desired value at the same method.
   * It starts the recursive iteration to find the door by the id from the root area of the tree.
   *
   * @param id The id of the door to retrieve.
   * @return The door corresponding to the id.
   */
  public static Door findDoorById(String id) {
    FindDoorByIdVisitor visitor = new FindDoorByIdVisitor(id);
    Area root = DirectoryAreas.getRootArea();
    root.accept(visitor);
    return visitor.door;
  }

  /**
   * A partition can contain other partition or spaces, so it iterates recursively its sub areas until the corresponding door
   * is found.
   *
   * @param partition The partition to check its id.
   */
  @Override
  public void visitPartition(Partition partition) {
    int i = 0;
    ArrayList<Area> areas = partition.getAreas();
    while (door != null && i < areas.size()) {
      areas.get(i).accept(this);
      i++;

    }
  }

  /**
   * A space can contain multiple doors, so it iterates on them to find the corresponding one.
   * @param space The space to check its id.
   */
  @Override
  public void visitSpace(Space space) {
    int i = 0;
    ArrayList<Door> doors = space.getDoors();
    while (door != null && i < doors.size()) {
      doors.get(i).accept(this);
      i++;
    }
  }

  /**
   * If the door corresponds to the specified id, the attribute door is modified and recursive iteration stops, else do nothing.
   * @param door
   */
  @Override
  public void visitDoor(Door door) {
    if(door.getId().equals(doorId)) {
      this.door = door;
    }
  }
}
