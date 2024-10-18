package baseNoStates;

import java.util.ArrayList;

public class Partition extends Area{

  private ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, Partition parentPartition){
    super(id, parentPartition);
    if(parentPartition != null){
      parentPartition.addArea(this);
    }
  }

  public Area findAreaById(String id){
    return super.findAreaById(id);
  }

  @Override
  public ArrayList<Space> getSpaces() {
    //create a Space array to store Space items
    ArrayList<Space> spaces = new ArrayList<>();

    //recursivily get the Space items from the Partition and sub Partitions
    for(Area area : areas){
      if(area instanceof Space){
        spaces.add((Space)area);
      }
      else if(area instanceof Partition){
        spaces.addAll(area.getSpaces());
      }
    }

    return spaces;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess(){
    return null;
  }

  @Override
  public String getId() {
    return this.id;
  }

  public void addArea(Area area){
    areas.add(area);
  }

}
