package basenostates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import basenostates.areas.DirectoryAreas;
import basenostates.doors.DirectoryDoors;
import basenostates.doors.doorstates.Clock;
import basenostates.users.DirectoryUsers;
import basenostates.users.usergroups.DirectoryUserGroups;

/**
 * Main class of the program, this is where the execution of the logic starts.
 */
public class Main {
  /**
   * Main method of the program, this is where the execution starts.
   *
   * @param args The arguments passed to the program. Ignored.
   */
  public static void main(String[] args) {
    DirectoryAreas.makeAreas();           // Create the areas
    DirectoryDoors.makeDoors();           // Create the doors
    DirectoryUserGroups.makeUserGroups(); // Create the user groups
    DirectoryUsers.makeUsers();           // Create the users
    Clock.getInstance().start();          // Start the clock
    new WebServer();                      // Start the web server
    Clock.getInstance().stop();           // Stop the clock
  }
}
