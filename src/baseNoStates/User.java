package baseNoStates;

import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private final ArrayList<Space> spaces = new ArrayList<>();

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

  private ArrayList<Space> getSpaces() {
    return spaces;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
