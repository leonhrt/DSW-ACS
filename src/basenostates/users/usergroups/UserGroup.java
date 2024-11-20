package basenostates.users.usergroups;

import basenostates.areas.Space;
import basenostates.users.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The UserGroup class represents a group of users with defined permissions.
 * Each group has a set of actions it can perform,
 * spaces it can access, and a schedule that determines
 * when those actions and accesses are allowed.
 */
public class UserGroup {
  private String name;
  private Set<String> actions;
  private ArrayList<User> users;
  private ArrayList<Space> availableSpaces;   // The accessible spaces of the group
  private Schedule schedule;


  /**
   * Constructs a UserGroup with the specified name, actions, users, available spaces, and schedule.
   *
   * @param name The name of the user group
   * @param actions The set of actions the group can perform
   * @param users The list of users in this group
   * @param availableSpaces The list of spaces the group can access
   * @param schedule The schedule governing the group's permissions
   */
  public UserGroup(String name, Set<String> actions, ArrayList<User> users,
                   ArrayList<Space> availableSpaces, Schedule schedule) {
    this.name = name;
    this.actions = actions;
    this.users = users;
    this.availableSpaces = availableSpaces;
    this.schedule = schedule;
  }

  /**
   * Constructs a UserGroup with the specified name and initializes empty sets of actions,
   * available spaces, users, and a default schedule.
   *
   * @param name The name of the user group
   */
  public UserGroup(String name) {
    this.name = name;
    this.actions = new HashSet<>();
    this.availableSpaces = new ArrayList<>();
    this.users = new ArrayList<>();
    this.schedule = new Schedule();
  }

  /**
   * Constructs a UserGroup with the specified name, actions, available spaces, and schedule.
   * Initializes an empty list of users.
   *
   * @param name The name of the user group
   * @param actions The set of actions the group can perform
   * @param availableSpaces The list of spaces the group can access
   * @param schedule The schedule governing the group's permissions
   */
  public UserGroup(String name, Set<String> actions, ArrayList<Space> availableSpaces,
                   Schedule schedule) {
    this.name = name;
    this.actions = actions;
    this.availableSpaces = availableSpaces;
    this.users = new ArrayList<>();
    this.schedule = schedule;
  }

  /**
   * Checks if the current LocalDateTime is within the allowed schedule for this group.
   *
   * @param now The current date and time to check against the group's schedule
   *
   * @return True if the current date and time are allowed, false otherwise
   */
  public boolean checkAllowedDateTime(LocalDateTime now) {
    return schedule.allowedDateTime(now);
  }

  /**
   * Determines if this group is allowed to perform a specific action.
   *
   * @param action The action to check
   *
   * @return True if the action is allowed, false otherwise
   */
  public boolean canDoAction(String action) {
    return actions.contains(action);
  }

  /**
   * Checks if this group has access to a specific space.
   *
   * @param space The space to check
   *
   * @return True if the group can access the space, false otherwise
   */
  public boolean userCanBeInSpace(Space space) {
    return availableSpaces.contains(space);
  }

  /**
   * Adds a User to this group.
   *
   * @param user The user to add to the group
   */
  public void addUser(User user) {
    this.users.add(user);
  }

  /**
   * Returns the name of this user group.
   *
   * @return the name of the group
   */
  public String getName() {
    return name;
  }
}
