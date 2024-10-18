package baseNoStates;

import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getCredential() {
    return credential;
  }

  public boolean canBeInSpace(Space space) {
    return true;
  }

  private ArrayList<Space> getSpaces(){
    return null;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
