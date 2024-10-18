package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryDoors {
  private static ArrayList<Door> allDoors;

  public static void makeDoors() {
    //getting the spaces
    Area exterior = DirectoryAreas.findAreaById("exterior");
    Area parking = DirectoryAreas.findAreaById("parking");
    Area stairs = DirectoryAreas.findAreaById("stairs");
    Area hall = DirectoryAreas.findAreaById("hall");
    Area room1 = DirectoryAreas.findAreaById("room1");
    Area room2 = DirectoryAreas.findAreaById("room2");
    Area corridor = DirectoryAreas.findAreaById("corridor");
    Area room3 = DirectoryAreas.findAreaById("room3");
    Area it = DirectoryAreas.findAreaById("it");

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

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
