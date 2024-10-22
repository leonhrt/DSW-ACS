package baseNoStates;

import java.time.LocalDateTime;
import java.util.ArrayList;

//TODO: Modify spaces to use UserGroup instead
public class User {
  private final String name;
  private final String credential;
  private final ArrayList<Space> spaces = new ArrayList<>();
  private UserGroup userGroup;

  public User(String name, String credential, ArrayList<Space> spaces) {
    this.name = name;
    this.credential = credential;
    this.spaces.addAll(spaces);
  }

  public String getCredential() {
    return credential;
  }

  public boolean canBeInSpace(Space space) {
    return spaces.contains(space);
  }

  public boolean canSendRequests(LocalDateTime now) {
    return userGroup.checkAllowedDateTime(now);
  }

  public boolean canDoAction(String action) {
    return userGroup.canDoAction(action);
  }

  private ArrayList<Space> getSpaces() {
    return spaces;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
