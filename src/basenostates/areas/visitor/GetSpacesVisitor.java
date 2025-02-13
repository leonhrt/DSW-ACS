package basenostates.areas.visitor;

import basenostates.areas.Area;
import basenostates.areas.Partition;
import basenostates.areas.Space;
import basenostates.doors.Door;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This visitor class is made to access the specified area
 * and retrieve a list of the spaces contained within the specified area and its sub-areas.
 */
public class GetSpacesVisitor implements Visitor {
  private final ArrayList<Space> spaces = new ArrayList<>();

  private static final Logger milestone2 = LoggerFactory.getLogger("secondMilestone");

  // Private constructor to force the static method
  private GetSpacesVisitor() {}

  /**
   * This static method solves the problem of getting wrong spaces
   * if the client reuses an already executed GetSpacesVisitor instance.
   * When reusing a GetSpaceVisitor instance,
   * new spaces are added to the list of spaces previously gotten.
   * This method generates a new instance every time is called,
   * so the client isn't able to reuse any instance.
   *
   * @param area The area to get spaces from.
   * @return The list of spaces included in the area.
   */
  public static ArrayList<Space> getSpaces(Area area) {
    milestone2.debug("Starting visitor getSpaces for the area: " + area);
    GetSpacesVisitor visitor = new GetSpacesVisitor();
    area.accept(visitor);
    milestone2.debug("getSpaces visitor found " + visitor.spaces.size() + " spaces");
    return visitor.spaces;
  }

  /**
   * Recursively get the Space items from the current partition and sub-partitions.
   *
   * @param partition The partition to get its spaces
   */
  @Override
  public void visitPartition(Partition partition) {
    milestone2.debug("getSpaces visiting partition: " + partition);
    for (Area area : partition.getAreas()) {
      area.accept(this);
    }
  }

  /**
   * Adds the space to the spaces list.
   *
   * @param space The space to add to the list
   */
  @Override
  public void visitSpace(Space space) {
    milestone2.debug("visitSpace visiting space: " + space);
    spaces.add(space);
  }

  /**
   * Empty method, as this visitor won't be using this method.
   *
   * @param door Ignored.
   */
  @Override
  public void visitDoor(Door door) {

  }
}
