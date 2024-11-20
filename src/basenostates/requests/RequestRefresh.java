package basenostates.requests;

import basenostates.doors.DirectoryDoors;
import basenostates.doors.Door;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that refresh a request sent by the user.
 */
public class RequestRefresh implements Request {
  private final ArrayList<JSONObject> jsonsDoors = new ArrayList<>();

  /**
   * Formats the answer to JSON format.
   *
   * @return The formatted answer as a JSONObject.
   */
  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("doors", new JSONArray(jsonsDoors));
    // jsonDoors has been set previously by process()
    return json;
  }

  /**
   * Overrides the toString method of java for objects.
   *
   * @return The String to display.
   */
  @Override
  public String toString() {
    return "RequestRefresh{" + jsonsDoors + "}";
  }

  // Also this is used to paint the simulator when the page is loaded, and to display
  // doors and readers after passing from locked to propped or propped to locked,
  // pressing the Refresh Request button of the simulator.
  // Also, to quickly test if the partition requests sent by the client app in Flutter
  // works or not, retrieves the state of all the doors so that the simulator can
  // repaint the readers

  /**
   * Re-paint the state of all doors, and if it's open or closed.
   */
  public void process() {
    for (Door door : DirectoryDoors.getAllDoors()) {
      jsonsDoors.add(door.toJson());
    }
  }
}
