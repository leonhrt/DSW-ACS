package baseNoStates;

import java.time.LocalDateTime;
import java.util.ArrayList;

//TODO: Modify spaces to use UserGroup instead
public class User {
  private final String name;
  private final String credential;
  private final UserGroup userGroup;

  public User(String name, String credential, UserGroup userGroup) {
    this.name = name;
    this.credential = credential;
    this.userGroup = userGroup;
    this.userGroup.addUser(this);
  }

  public String getCredential() {
    return credential;
  }

  public boolean canBeInSpace(Space space) {
    return userGroup.userCanBeInSpace(space);
  }

  public boolean canSendRequests(LocalDateTime now) {
    return userGroup.checkAllowedDateTime(now);
  }

  public boolean canDoAction(String action) {
    return userGroup.canDoAction(action);
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
