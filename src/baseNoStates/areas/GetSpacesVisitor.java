package baseNoStates.areas;

import baseNoStates.doors.Door;

import java.util.ArrayList;

public class GetSpacesVisitor implements Visitor {
  private ArrayList<Space> spaces = new ArrayList<>();

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
    GetSpacesVisitor visitor = new GetSpacesVisitor();
    area.accept(visitor);
    return visitor.spaces;
  }

  @Override
  public void visitPartition(Partition partition) {
    // Recursively get the Space items from the current Partition and sub Partitions
    for (Area area : partition.getAreas()) {
      area.accept(this);
    }
  }

  @Override
  public void visitSpace(Space space) {
    spaces.add(space);
  }

  @Override
  public void visitDoor(Door door) {

  }
}
