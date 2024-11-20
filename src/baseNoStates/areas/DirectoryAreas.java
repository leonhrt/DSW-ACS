package baseNoStates.areas;


/**
 * The DirectoryAreas class manages the creation and retrieval of areas and doors.
 */
public class DirectoryAreas {
  private static Area rootArea;


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
    new Space("parking", basement);
    new Space("hall", ground_floor);
    new Space("room1", ground_floor);
    new Space("room2", ground_floor);
    new Space("room3", floor1);
    new Space("corridor", floor1);
    new Space("it", floor1);
    new Space("stairs", building);
    new Space("exterior", building);
  }

  public static Area getRootArea() {
    return rootArea;
  }
}
