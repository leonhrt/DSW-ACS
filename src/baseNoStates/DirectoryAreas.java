package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class DirectoryAreas {
  private static Area rootArea;
  private static ArrayList<Door> allDoors = new ArrayList<>();
  private static ArrayList<Area> allAreas = new ArrayList<>();

  public static void makeAreas() {
    //partition of partitions
    Partition building = new Partition("building", null);
    rootArea = building;

    //partitions of spaces
    Partition basement = new Partition("basement", building);
    Partition groundFloor = new Partition("groundFloor", building);
    Partition floor1 = new Partition("floor1", building);

    //spaces
    Space parking = new Space("parking", basement);
    Space hall = new Space("hall", groundFloor);
    Space room1 = new Space("room1", groundFloor);
    Space room2 = new Space("room2", groundFloor);
    Space room3 = new Space("room3", floor1);
    Space corridor = new Space("corridor", floor1);
    Space it = new Space("it", floor1);
    Space stairs = new Space("stairs", building);
    Space exterior = new Space("exterior", building);

    allAreas.addAll(Arrays.asList(building, basement, groundFloor, floor1, stairs, exterior, parking, hall, room1, room2, room3, corridor, it));
  }

  //find an area of the allAreas ArrayList by its id
  //we get a stream filtered by id, and we return the first, or if there isn't any, null
  public static Area findAreaById(String id) {
    Optional<Area> area = allAreas.stream().filter(a -> a.getId().equals(id)).findFirst();
    return area.orElse(null);
  }

  public static Door findDoorById(String id) {
    Optional<Door> door = allDoors.stream().filter(d -> d.getId().equals(id)).findFirst();
    return door.orElse(null);
  }

  public static ArrayList<Door> getAllDoors() {
    return allDoors;
  }

  //add the Door if it isn't on the current allDoors list
  public static void addDoor(Door door){
    if(!allDoors.contains(door)){
      allDoors.add(door);
    }
  }
}
