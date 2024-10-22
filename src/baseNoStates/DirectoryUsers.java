package baseNoStates;

import java.util.ArrayList;
import java.util.List;

public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  public static void makeUsers() {
    //TODO: make user groups according to the specifications in the comments, because
    // now all are the same

    /*
    These are the groups that belong to each user
    Administrator: Ana
    Manager: Manel, Marta
    Employee: Ernest, Eulalia
    Blank: Bernat, Blai
     */
    UserGroup adminGroup = DirectoryUserGroups.getUserGroupByName("Administrator");
    UserGroup managerGroup = DirectoryUserGroups.getUserGroupByName("Manager");
    UserGroup employeeGroup = DirectoryUserGroups.getUserGroupByName("Employee");
    UserGroup blankGroup = DirectoryUserGroups.getUserGroupByName("Blank");

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    users.add(new User("Bernat", "12345", blankGroup));
    users.add(new User("Blai", "77532", blankGroup));

    // employees :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    users.add(new User("Ernest", "74984", employeeGroup));
    users.add(new User("Eulalia", "43295", employeeGroup));


    // managers :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    users.add(new User("Manel", "95783", managerGroup));
    users.add(new User("Marta", "05827", managerGroup));

    //admins have the same spaces assigned as managers

    // admin :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces
    users.add(new User("Ana", "11343", adminGroup));
  }

  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
