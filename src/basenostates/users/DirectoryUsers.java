package basenostates.users;

import basenostates.users.usergroups.DirectoryUserGroups;
import basenostates.users.usergroups.UserGroup;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DirectoryUsers class manages a collection of users within the system.
 * It provides methods for creating and retrieving users based on their credentials.
 */
public final class DirectoryUsers {
  private static final ArrayList<User> users = new ArrayList<>();

  private static final Logger milestone1 = LoggerFactory.getLogger("firstMilestone");
  /**
   * Initializes the users list with predefined user accounts
   * and their associated groups.
   *
   * <p>This method creates user accounts with specific groups and
   * privileges. The user groups include Administrator, Manager, Employee,
   * and Blank, each with defined access rights.
   */
  public static void makeUsers() {
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
    // just open, close and shortly unlock
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

  /**
   * Searches for a user in the list by their credential.
   *
   * @param credential the credential of the user to search for
   * @return the User object if found, otherwise returns null and prints a message
   */
  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    milestone1.warn("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
