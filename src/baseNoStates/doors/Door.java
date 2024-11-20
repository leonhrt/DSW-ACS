package baseNoStates.doors;

import baseNoStates.areas.Space;
import baseNoStates.areas.Visitor;
import baseNoStates.doors.doorstates.Actions;
import baseNoStates.doors.doorstates.DoorState;
import baseNoStates.doors.doorstates.UnlockedState;
import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

/**
 * The Door class represents a physical door within a building.
 * A door can be in one of several states, these are represented by the DoorState class.
 * Also, the user can interact with the door with open and close actions if they have the right permissions.
 *
 * This class is a part of the State Pattern implemented for the DoorStates
 */
public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState state;
  private final Space fromSpace;
  private final Space toSpace;

  /**
   * Door constructor that sets the door's ID, its state, and its spaces.
   *
   * @param id         The unique identifier for the door.
   * @param fromSpace  The space from which the door opens.
   * @param toSpace    The space to which the door leads.
   */
  public Door(String id, Space fromSpace, Space toSpace) {
    this.id = id;
    closed = true;
    this.state = new UnlockedState(this);
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;

    //we add this Door to Spaces fromSpace and toSpace
    fromSpace.addDoor(this);
    toSpace.addDoor(this);
  }

  /**
   * Process a request for an action on the door.
   * Only process the action if the request is authorized.
   *
   * @param request RequestReader object containing the request to be processed.
   */
  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  /**
   * Executes a specific action on the door.
   * Possible actions are open, close, lock, unlock, and unlock shortly.
   *
   * @param action The action to be executed.
   */
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        state.unlockShortly();
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public void accept(Visitor visitor){
    visitor.visitDoor(this);
  }

  /**
   * Sets the door state.
   * @param state
   */
  public void setState(DoorState state) {
    this.state = state;
  }

  /**
   * Checks if the door is closed.
   *
   * @return True if the door is closed, false otherwise.
   */
  public boolean isClosed() {
    return closed;
  }

  /**
   * Sets the closed state of the door.
   *
   * @param closed The new closed state of the door.
   */
  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  /**
   * Returns the door's ID.
   *
   * @return The door's ID.
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the name of the door's state.
   *
   * @return The name of the door's state.
   */
  public String getStateName() {
    return state.getStateName();
  }

  /**
   * Returns a string representation of the door.
   *
   * @return String with door information
   */
  @Override
  public String toString() {
    return "Door{" + ", id='" + id + '\'' + ", closed=" + closed + ", state=" + getStateName() + "}";
  }

  /**
   * Converts the door information to a JSON object.
   *
   * @return JSONObject with the door's information.
   */
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  /**
   * Gets the source space connected by this door.
   *
   * @return Space representing the source location
   */
  public Space getFromSpace() {
    return this.fromSpace;
  }

  /**
   * Gets the destination space connected by this door.
   *
   * @return Space representing the destination location
   */
  public Space getToSpace() {
    return this.toSpace;
  }
}
