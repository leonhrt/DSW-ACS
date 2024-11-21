package basenostates.areas.visitor;

import basenostates.areas.Area;
import basenostates.areas.Partition;
import basenostates.areas.Space;
import basenostates.doors.Door;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This visitor class accesses the specified area in order to get a list of doors
 * that provide access from outside this area.
 */
public class GetDoorsGivingAccessVisitor implements Visitor {
  private final ArrayList<Door> doors = new ArrayList<>();

  private static final Logger milestone2 = LoggerFactory.getLogger("secondMilestone");

  private GetDoorsGivingAccessVisitor() {}

  /**
   * This static methods ensures that a single instance of the visitor
   * is not executed more than once, by creating an instance
   * and returning the desired value at the same method.
   *
   * @param area The area to retrieve all its giving access doors.
   * @return A list of all doors that give access to that area.
   */
  public static ArrayList<Door> getDoorsGivingAccess(Area area) {
    milestone2.debug("Starting visitor getDoorsGivingAccess to area: " + area);
    GetDoorsGivingAccessVisitor visitor = new GetDoorsGivingAccessVisitor();
    area.accept(visitor);
    milestone2.debug("getDoorsGivingAccess visitor found " + visitor.doors.size() + " doors");
    return visitor.doors;
  }

  /**
   * Get all spaces within the partition using the getSpacesVisitor
   * and then get all the doors within the spaces.
   * Finally, the spaces retrieved using getSpaceVisitor are used
   * to check if each door is giving access to the
   * partition, or it's just connecting two spaces both contained within the partition.
   *
   * @param partition The partition to retrieve all the giving access doors.
   */
  @Override
  public void visitPartition(Partition partition) {
    milestone2.debug("getDoorsGivingAccess visiting partition: " + partition);
    ArrayList<Space> spaces = GetSpacesVisitor.getSpaces(partition);

    // Recursively get the Space items from the current Partition and sub Partitions
    for (Space space : spaces) {
      space.accept(this);
    }

    // Once all doors are retrieved, remove the ones that connect two spaces both contained within the partition.
    // So we only keep the ones that really give access to the partition. Also keep the exterior area so its doors are
    // always detected as doors that give access from outside.
    milestone2.debug("getDoorsGivingAccess visitor found " + doors.size() + " doors inside the partition "
     + partition + ", removing doors that doesn't give access from the outside");
    for (int i = doors.size() - 1; i >= 0; i--) {
      if (spaces.contains(doors.get(i).getFromSpace()) && spaces.contains(doors.get(i).getToSpace())
          && !doors.get(i).getFromSpace().getId().equals("exterior") && !doors.get(i).getToSpace().getId().equals("exterior")){
        doors.remove(i);
      }
    }
  }

  /**
   * When retrieving the doors that give access to a specific space, the output is all it's doors.
   *
   * @param space The space to retrieve the doors giving access to it.
   */
  @Override
  public void visitSpace(Space space) {
    milestone2.debug("getDoorsGivingAccess visiting space: " + space);

    // Recursively get the Door items from the current Space
    for (Door door : space.getDoors()) {
      door.accept(this);
    }
  }

  /**
   * Add the door to the doors list.
   *
   * @param door The door to add to the list.
   */
  @Override
  public void visitDoor(Door door) {
    milestone2.debug("getDoorsGivingAccess visiting and adding door: " + door);
    doors.add(door);
  }
}
