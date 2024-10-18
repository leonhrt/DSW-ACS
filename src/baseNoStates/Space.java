package baseNoStates;

import java.util.ArrayList;
import java.util.List;

public class Space extends Area {
  private final String name;
  private final Partition parentPartition;
  private ArrayList<Door> doors;

  public Space(String name, Partition parentPartition){
    this.name = name;
    this.parentPartition = parentPartition;
  }

  //return an ArrayList containing just this Space
  @Override
  public ArrayList<Space> getSpaces() {
    return new ArrayList<Space>(List.of(this));
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess(){
    return doors;
  }
}
