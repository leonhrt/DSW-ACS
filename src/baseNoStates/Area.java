package baseNoStates;

import java.util.ArrayList;

public abstract class Area {
  public abstract ArrayList<Space> getSpaces();
  public abstract ArrayList<Door> getDoorsGivingAccess();
}
