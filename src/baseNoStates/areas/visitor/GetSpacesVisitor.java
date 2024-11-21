package baseNoStates.areas.visitor;

import baseNoStates.areas.Area;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import baseNoStates.doors.Door;

import java.util.ArrayList;

/**
 * This visitor class is made to access the specified area and retrieve a list of the spaces contained within the specified area and its sub-areas.
 */
public class GetSpacesVisitor implements Visitor {
  private final ArrayList<Space> spaces = new ArrayList<>();

  // Private constructor to force the static method
  private GetSpacesVisitor() {}

  /**
   * This static method solves the problem of getting wrong spaces if the client reuses an already executed GetSpacesVisitor instance.
   * When reusing a GetSpaceVisitor instance, new spaces are added to the list of spaces previously gotten.
   * This method generates a new instance every time is called, so the client isn't able to reuse any instance.
   * @param area The area to get spaces from.
   * @return The list of spaces included in the area.
   */
  public static ArrayList<Space> getSpaces(Area area) {
    //TODO System.out.println("Starting visitor getSpaces for the area: " + area);
    GetSpacesVisitor visitor = new GetSpacesVisitor();
    area.accept(visitor);
    //TODO System.out.println("getSpaces visitor found " + visitor.spaces.size() + " spaces");
    return visitor.spaces;
  }

  /**
   * Recursively get the Space items from the current partition and sub-partitions
   * @param partition The partition to get its spaces
   */
  @Override
  public void visitPartition(Partition partition) {
    //TODO System.out.println("getSpaces visiting partition: " + partition);
    for (Area area : partition.getAreas()) {
      area.accept(this);
    }
  }

  /**
   * Adds the space to the spaces list
   * @param space The space to add to the list
   */
  @Override
  public void visitSpace(Space space) {
    //TODO System.out.println("visitSpace visiting space: " + space);
    spaces.add(space);
  }

  @Override
  public void visitDoor(Door door) {

  }
}
