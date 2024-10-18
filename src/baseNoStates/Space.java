package baseNoStates;

import java.util.ArrayList;
import java.util.List;

public class Space extends Area {

  private ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Partition parentPartition){
    super(id, parentPartition);
    parentPartition.addArea(this);
  }

  public Area findAreaById(String id){
    return super.findAreaById(id);
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

  @Override
  public String getId() {
    return this.id;
  }


}
