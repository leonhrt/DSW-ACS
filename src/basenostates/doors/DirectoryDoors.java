package basenostates.doors;

import basenostates.areas.Space;
import basenostates.areas.visitor.FindAreaByIdVisitor;

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
   * This method creates doors that connect various spaces such as
   * exterior, parking, stairs, halls, and rooms.
   */
  public static void makeDoors() {
    //getting the spaces
    Space exterior = (Space) FindAreaByIdVisitor.findAreaById("exterior");
    Space parking = (Space) FindAreaByIdVisitor.findAreaById("parking");
    Space stairs = (Space) FindAreaByIdVisitor.findAreaById("stairs");
    Space hall = (Space) FindAreaByIdVisitor.findAreaById("hall");
    Space room1 = (Space) FindAreaByIdVisitor.findAreaById("room1");
    Space room2 = (Space) FindAreaByIdVisitor.findAreaById("room2");
    Space corridor = (Space) FindAreaByIdVisitor.findAreaById("corridor");
    Space room3 = (Space) FindAreaByIdVisitor.findAreaById("room3");
    Space it = (Space) FindAreaByIdVisitor.findAreaById("it");

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
