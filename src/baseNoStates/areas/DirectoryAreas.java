package baseNoStates.areas;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * The DirectoryAreas class manages the creation and retrieval of areas and doors.
 */
public class DirectoryAreas {
  private static Area rootArea;
  private static final ArrayList<Area> allAreas = new ArrayList<>();


  /**
   * Creates and initializes the areas within the structure.
   * This method sets up the hierarchy of partitions and spaces,
   * starting from the root area partition down to specific spaces.
   */
  public static void makeAreas() {
    // partition of partitions
    Partition building = new Partition("building", null);

    // define the building partition as the root area of the tree
    rootArea = building;

    // partitions of spaces
    Partition basement = new Partition("basement", building);
    Partition ground_floor = new Partition("ground_floor", building);
    Partition floor1 = new Partition("floor1", building);

    // spaces
    Space parking = new Space("parking", basement);
    Space hall = new Space("hall", ground_floor);
    Space room1 = new Space("room1", ground_floor);
    Space room2 = new Space("room2", ground_floor);
    Space room3 = new Space("room3", floor1);
    Space corridor = new Space("corridor", floor1);
    Space it = new Space("it", floor1);
    Space stairs = new Space("stairs", building);
    Space exterior = new Space("exterior", building);

    allAreas.addAll(Arrays.asList(building, basement, ground_floor, floor1, stairs, exterior, parking, hall, room1, room2, room3, corridor, it));
  }

  public static Area getRootArea() {
    return rootArea;
  }
}
