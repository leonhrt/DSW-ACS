package basenostates.areas;


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
    Partition property = new Partition("property", null);
    Partition building = new Partition("building", property);

    // define the property partition as the root area of the tree
    rootArea = property;

    // partitions of spaces
    final Partition basement = new Partition("basement", building);
    final Partition groundFloor = new Partition("ground_floor", building);
    final Partition floor1 = new Partition("floor1", building);

    // spaces
    new Space("parking", basement);
    new Space("hall", groundFloor);
    new Space("room1", groundFloor);
    new Space("room2", groundFloor);
    new Space("room3", floor1);
    new Space("corridor", floor1);
    new Space("it", floor1);
    new Space("stairs", building);
    new Space("exterior", property);
  }

  public static Area getRootArea() {
    return rootArea;
  }
}
