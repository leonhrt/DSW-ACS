package baseNoStates;

import java.util.ArrayList;

public abstract class Area {
  protected String id;
  protected Partition parentPartition;

  public Area(String id, Partition parentPartition){
    this.id = id;
    this.parentPartition = parentPartition;
  }

  public Area findAreaById(String id){
    return DirectoryAreas.findAreaById(id);
  }

  public abstract ArrayList<Space> getSpaces();
  public abstract ArrayList<Door> getDoorsGivingAccess();
  public abstract String getId();
}
