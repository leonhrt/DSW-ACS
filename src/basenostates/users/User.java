package basenostates.users;

import basenostates.areas.Space;
import basenostates.users.user_groups.UserGroup;

import java.time.LocalDateTime;

/**
 * The User class represents an entity within the system. that holds name, credential and belongs to a userGroup.
 * It allows interaction with the system features based on the user's group, such as actions and spaces.
 */
public class User {
  private final String name;
  private final String credential;
  private final UserGroup userGroup;

  /**
   * Constructor for the User class. To ensure that the user belongs to a group, we add the group to the user.
   * That is why we first create the groups and then the users.
   *
   * @param name The name of the user.
   * @param credential The identifier of the user.
   * @param userGroup Te group to which the user belongs.
   */
  public User(String name, String credential, UserGroup userGroup) {
    this.name = name;
    this.credential = credential;
    this.userGroup = userGroup;
    this.userGroup.addUser(this);
  }

  /**
   * Retrieves the credential of the user.
   *
   * @return the credential of the user.
   */
  public String getCredential() {
    return credential;
  }

  /**
   * Determines if the user can be in a given space.
   * The permissions are handled by the user's group.
   *
   * @param space The space to check for user access.
   *
   * @return If the user can be in the given space.
   */
  public boolean canBeInSpace(Space space) {
    return userGroup.userCanBeInSpace(space);
  }

  /**
   * Checks if the user can send requests to the system at a given time.
   * The permissions are handled by the user's group.
   *
   * @param now The current date and time.
   *
   * @return If the user is allowed to send requests at the given time.
   */
  public boolean canSendRequests(LocalDateTime now) {
    return userGroup.checkAllowedDateTime(now);
  }

  /**
   * Verifies if the user is authorized to perform a specified action.
   *
   * @param action The specified action.
   *
   * @return If the user is allowed to perform the action.
   */
  public boolean canDoAction(String action) {
    return userGroup.canDoAction(action);
  }

  /**
   * Provides a string representation of the user object, including the user's
   * name and credential information.
   *
   * @return a string describing the user
   */
  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
