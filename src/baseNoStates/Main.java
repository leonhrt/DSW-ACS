package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import baseNoStates.areas.DirectoryAreas;
import baseNoStates.doors.DirectoryDoors;
import baseNoStates.doors.doorstates.Clock;
import baseNoStates.users.user_groups.DirectoryUserGroups;
import baseNoStates.users.DirectoryUsers;

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
