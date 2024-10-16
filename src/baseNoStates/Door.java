package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

public class Door {
  private final String id;
  private boolean closed; // physically
  private boolean locked;
  private DoorState state;

  public Door(String id) {
    this.id = id;
    closed = true;
    this.state = new UnlockedState();
  }

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

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open(this);
        break;
      case Actions.CLOSE:
        state.close(this);
        break;
      case Actions.LOCK:
        state.lock(this);
        break;
      case Actions.UNLOCK:
        state.unlock(this);
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public void setState(DoorState state) {
    this.state = state;
  }

  public boolean isClosed() {
    return closed;
  }

  public boolean isLocked() { return locked; }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return "unlocked";
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", locked=" + locked
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    json.put("locked", locked);
    return json;
  }
}
