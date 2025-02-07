package basenostates.areas;

import basenostates.areas.visitor.GetDoorsGivingAccessVisitor;
import basenostates.areas.visitor.Visitor;
import basenostates.doors.Door;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The Space class represents a leaf object in the Composite design pattern.
 * It does not contain any child objects, but it implements the common interface
 * defined by the Area class, allowing clients to treat it uniformly.
 *
 * <p>Spaces can contain doors and provide access to other areas.
 */
public class Space extends Area {

  private final ArrayList<Door> doors = new ArrayList<>();

  /**
   * Constructs a Space with the specified ID and parent partition.
   * This constructor adds the space to its parent's area list, if it does have one.
   *
   * @param id              the unique identifier for the space
   * @param parentPartition the partition containing this space
   */
  public Space(String id, Partition parentPartition) {
    super(id, parentPartition);
  }

  /**
   * Executes the corresponding visitSpace method of the visitor.
   *
   * @param visitor The visitor to accept and execute
   */
  @Override
  public void accept(Visitor visitor) {
    visitor.visitSpace(this);
  }

  /**
   * Getter of the doors.
   *
   * @return The list of the doors that give access to this space.
   */
  public ArrayList<Door> getDoors() {
    return doors;
  }

  /**
   * Adds a door to this space's list of doors,
   * ensuring it is not added more than once.
   *
   * @param door the door to be added to this space
   */
  public void addDoor(Door door) {
    if (!doors.contains(door)) {
      doors.add(door);
    }
  }

  public JSONObject toJson(int depth) { // depth not used here
    JSONObject json = new JSONObject();
    json.put("class", "space");
    json.put("id", id);
    JSONArray jsonDoors = new JSONArray();
    for (Door d : GetDoorsGivingAccessVisitor.getDoorsGivingAccess(this)) {
      jsonDoors.put(d.toJson());
    }
    json.put("access_doors", jsonDoors);
    return json;
  }
}
