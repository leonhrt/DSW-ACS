package baseNoStates.doors;

import baseNoStates.areas.DirectoryAreas;
import baseNoStates.areas.Space;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The DirectoryDoors class manages a collection of doors within the
 * system. It provides methods for creating doors and retrieving them by their ID.
 */
public final class DirectoryDoors {
  private static ArrayList<Door> allDoors;


  /**
   * Initializes the doors list with predefined door connections
   * between spaces in the system.
   *
   * This method creates doors that connect various spaces such as
   * exterior, parking, stairs, halls, and rooms.
   */
  public static void makeDoors() {
    //getting the spaces
    Space exterior = (Space) DirectoryAreas.findAreaById("exterior");
    Space parking = (Space) DirectoryAreas.findAreaById("parking");
    Space stairs = (Space) DirectoryAreas.findAreaById("stairs");
    Space hall = (Space) DirectoryAreas.findAreaById("hall");
    Space room1 = (Space) DirectoryAreas.findAreaById("room1");
    Space room2 = (Space) DirectoryAreas.findAreaById("room2");
    Space corridor = (Space) DirectoryAreas.findAreaById("corridor");
    Space room3 = (Space) DirectoryAreas.findAreaById("room3");
    Space it = (Space) DirectoryAreas.findAreaById("it");

    // basement
    Door d1 = new Door("D1", exterior, parking); // exterior, parking
    Door d2 = new Door("D2", stairs, parking); // stairs, parking

    // ground floor
    Door d3 = new Door("D3", exterior, hall); // exterior, hall
    Door d4 = new Door("D4", stairs, hall); // stairs, hall
    Door d5 = new Door("D5", hall, room1); // hall, room1
    Door d6 = new Door("D6", hall, room2); // hall, room2

    // first floor
    Door d7 = new Door("D7", stairs, corridor); // stairs, corridor
    Door d8 = new Door("D8", corridor, room3); // corridor, room3
    Door d9 = new Door("D9", corridor, it); // corridor, IT

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  /**
   * Searches for a door in the list by its ID.
   *
   * @param id the ID of the door to search for
   * @return the Door object if found, otherwise returns null and prints a message
   */
  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  /**
   * Retrieves a list of all doors in the system.
   * (This is needed by RequestRefresh)
   *
   * @return an ArrayList of all doors
   */
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
