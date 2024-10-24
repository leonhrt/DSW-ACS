package baseNoStates.areas;

import baseNoStates.doors.Door;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * The DirectoryAreas class manages the creation and retrieval of areas and doors.
 */
public class DirectoryAreas {
  private static Area rootArea;
  private static ArrayList<Door> allDoors = new ArrayList<>();
  private static ArrayList<Area> allAreas = new ArrayList<>();


  /**
   * Creates and initializes the areas within the structure.
   * This method sets up the hierarchy of partitions and spaces,
   * starting from the root area partition down to specific spaces.
   */
  public static void makeAreas() {
    //partition of partitions
    Partition building = new Partition("building", null);
    rootArea = building;

    //partitions of spaces
    Partition basement = new Partition("basement", building);
    Partition ground_floor = new Partition("ground_floor", building);
    Partition floor1 = new Partition("floor1", building);

    //spaces
    Space parking = new Space("parking", basement);
    Space hall = new Space("hall", ground_floor);
    Space room1 = new Space("room1", ground_floor);
    Space room2 = new Space("room2", ground_floor);
    Space room3 = new Space("room3", floor1);
    Space corridor = new Space("corridor", floor1);
    Space it = new Space("it", floor1);
    Space stairs = new Space("stairs", building);
    Space exterior = new Space("exterior", null);

    allAreas.addAll(Arrays.asList(building, basement, ground_floor, floor1, stairs, exterior, parking, hall, room1, room2, room3, corridor, it));
  }


  /**
   * Finds an area by its ID in the list of all areas.
   *
   * @param id the ID of the area to find
   * @return the area with the specified ID, or null if not found
   */
  public static Area findAreaById(String id) {
    //we get a stream filtered by id, and we return the first, or if there isn't any, null
    Optional<Area> area = allAreas.stream().filter(a -> a.getId().equals(id)).findFirst();
    return area.orElse(null);
  }

  /**
   * Finds a door by its ID in the list of all doors.
   *
   * @param id the ID of the door to find
   * @return the door with the specified ID, or null if not found
   */
  public static Door findDoorById(String id) {
    Optional<Door> door = allDoors.stream().filter(d -> d.getId().equals(id)).findFirst();
    return door.orElse(null);
  }

  public static ArrayList<Door> getAllDoors() {
    return allDoors;
  }

  public static ArrayList<Area> getAllAreas() {
    return allAreas;
  }

  /**
   * Adds a door to the list of all doors if it is not already present.
   *
   * @param door the door to be added
   */
  public static void addDoor(Door door) {
    if (!allDoors.contains(door)) {
      allDoors.add(door);
    }
  }
}
