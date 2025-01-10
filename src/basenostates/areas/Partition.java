package basenostates.areas;

import basenostates.areas.visitor.Visitor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The Partition class represents a composite object in the Composite design pattern.
 * It can contain other Area objects, such as Space instances or other Partition instances.
 *
 * <p>This allows for the creation of a tree structure where partitions can contain multiple areas,
 * enabling hierarchical organization and manipulation of the entire structure.
 */
public class Partition extends Area {

  private final ArrayList<Area> areas = new ArrayList<>();

  /**
   * Constructs a Partition with the specified ID and parent partition.
   * This constructor adds the partition to its parent's area list, if it has one.
   *
   * @param id              The unique identifier for the partition
   * @param parentPartition The parent partition containing this partition,
   *                        or null if it doesn't have one.
   */
  public Partition(String id, Partition parentPartition) {
    super(id, parentPartition);
  }

  /**
   * Executes the corresponding visitPartition method of the visitor.
   *
   * @param visitor The visitor to accept and execute
   */
  @Override
  public void accept(Visitor visitor) {
    visitor.visitPartition(this);
  }

  /**
   * Getter of the areas.
   *
   * @return The areas list, which contains the areas within this partition.
   */
  public ArrayList<Area> getAreas() {
    return areas;
  }

  /**
   * Adds an Area to this Partition's collection of areas,
   * ensuring it is not added more than once.
   * This method allows for the dynamic addition of areas to the composite structure.
   *
   * @param area the Area to be added
   */
  public void addArea(Area area) {
    if (!areas.contains(area)) {
      areas.add(area);
    }
  }

  public JSONObject toJson(int depth) {
    // for depth=1 only the root and children,
    // for recusive = all levels use Integer.MAX_VALUE
    JSONObject json = new JSONObject();
    json.put("class", "partition");
    json.put("id", id);
    JSONArray jsonAreas = new JSONArray();
    if (depth > 0) {
      for (Area a : areas) {
        jsonAreas.put(a.toJson(depth - 1));
      }
      json.put("areas", jsonAreas);
    }
    return json;
  }
}
