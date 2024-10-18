package baseNoStates;

import java.util.ArrayList;

public class Partition extends Area{

  private final String name;
  private final Partition parentPartition;
  private ArrayList<Space> spaces;

  public Partition(String name, Partition parentPartition){
    this.name = name;
    this.parentPartition = parentPartition;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    return null;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess(){
    return null;
  }
}
