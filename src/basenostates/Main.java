package basenostates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import basenostates.areas.DirectoryAreas;
import basenostates.doors.DirectoryDoors;
import basenostates.doors.doorstates.Clock;
import basenostates.users.usergroups.DirectoryUserGroups;
import basenostates.users.DirectoryUsers;

public class Main {
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
