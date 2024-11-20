package basenostates.requests;

import basenostates.areas.visitor.FindDoorByIdVisitor;
import basenostates.doors.Door;
import basenostates.users.DirectoryUsers;
import basenostates.users.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that handles the reading requests.
 */
public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  /**
   * Main constructor of the class. Sets all the attributes passed as parameter.
   *
   * @param credential The credential of the user doing the request.
   * @param action The action that the user wants to perform.
   * @param now When the request was sent.
   * @param doorId The door which the action wants to be performed.
   */
  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  /**
   * Setter of the door state name.
   *
   * @param name The name of the state of the door.
   */
  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  /**
   * Getter of the action.
   *
   * @return The action performed.
   */
  public String getAction() {
    return action;
  }

  /**
   * Getter to check if the user is authorized.
   *
   * @return True if the user is authorized, False otherwise.
   */
  public boolean isAuthorized() {
    return authorized;
  }

  /**
   * Adds the reason passed as parameter to the list.
   *
   * @param reason The reason explaining why of the decision made.
   */
  public void addReason(String reason) {
    reasons.add(reason);
  }


  /**
   * Overrides the toString method of java for objects.
   *
   * @return The String to display.
   */
  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now + ", doorID="
            + doorId + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  /**
   * Formats the answer to JSON format.
   *
   * @return The formatted answer as a JSONObject.
   */
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.

  /**
   * Check if the user can perform the action given.
   */
  public void process() {
    User user = DirectoryUsers.findUserByCredential(credential);
    Door door = FindDoorByIdVisitor.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing

  /**
   * Check if the user is authorized to perform the given action.
   *
   * @param user The user that wants to perform the action.
   * @param door Which door the user wants to perform the action.
   */
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
    } else {
      authorized = user.canSendRequests(now)            // Who and when
              && user.canBeInSpace(door.getFromSpace()) // Where
              && user.canBeInSpace(door.getToSpace())   // Where
              && user.canDoAction(action);              // What
    }
  }
}

