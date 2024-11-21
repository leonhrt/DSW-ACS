package baseNoStates.areas.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseNoStates.areas.Area;
import baseNoStates.areas.DirectoryAreas;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import baseNoStates.doors.Door;

import java.util.ArrayList;

/**
 * This visitor class accesses to the root area and look in all its tree for the specified area until it finds it,
 * or returns null if it doesn't exist.
 */
public class FindAreaByIdVisitor implements Visitor {
  private Area area;
  private final String areaId;

  private static final Logger milestone2 = LoggerFactory.getLogger("secondMilestone");

  private FindAreaByIdVisitor(String id) {
    this.areaId = id;
    this.area = null;
  }

  /**
   * This static methods ensures that a single instance of the visitor is not executed more than once, by creating an
   * instance and returning the desired value at the same method.
   * It starts the recursive iteration to find the area by the id from the root area of the tree.
   * @param id The id of the area to retrieve.
   * @return The area corresponding to the id.
   */
  public static Area findAreaById(String id) {
    milestone2.debug("Starting visitor findAreaById, id: " + id);
    FindAreaByIdVisitor visitor = new FindAreaByIdVisitor(id);
    Area root = DirectoryAreas.getRootArea();
    root.accept(visitor);
    milestone2.debug("findAreaById found " + visitor.area + " as " + visitor.areaId);
    return visitor.area;
  }

  /**
   * A partition can contain other partition or spaces, so if the partition corresponds to the id, area is the partition
   * and the recursive iteration ends, otherwise it iterates recursively its sub areas until the corresponding area
   * is found.
   * @param partition The partition to check its id.
   */
  @Override
  public void visitPartition(Partition partition) {
    milestone2.debug("findAreaById visiting partition: " + partition);
    if (partition.getId().equals(areaId)) {
      area = partition;
      milestone2.debug("findAreaById visitor found " + partition + " as area id: " + areaId);
    } else {
      int i = 0;
      ArrayList<Area> areas = partition.getAreas();
      while (area == null && i < areas.size()) {
        areas.get(i).accept(this);
        i++;
      }
    }
  }

  /**
   * A space cannot contain any other sub areas, so if the id corresponds to the space, area is modified, otherwise do nothing.
   * @param space The space to check its id.
   */
  @Override
  public void visitSpace(Space space) {
    milestone2.debug("findAreaById visiting space: " + space);
    if(space.getId().equals(areaId)) {
      milestone2.debug("findAreaById visitor found " + space + " as area id: " + areaId);
      area = space;
    }
  }

  @Override
  public void visitDoor(Door door) {

  }
}
