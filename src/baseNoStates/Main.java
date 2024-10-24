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
    DirectoryAreas.makeAreas();
    DirectoryDoors.makeDoors();
    DirectoryUserGroups.makeUserGroups();
    DirectoryUsers.makeUsers();
    /*for (UserGroup userGroup : DirectoryUserGroups.userGroups) {
      System.out.println(userGroup.name);
      for (User user : userGroup.users) {
        System.out.println("  " + user.name);
      }
    }*/
    Clock.getInstance().start();
    new WebServer();
    Clock.getInstance().stop();
  }
}
