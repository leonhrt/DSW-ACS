package basenostates.requests;

import org.json.JSONObject;


/**
 * The Request interface defines the contract for handling requests
 * within the system.
 */
public interface Request {
  /**
   * Method that returns the answer as a JSON object.
   *
   * @return The information formatted to a JSON object.
   */
  JSONObject answerToJson();

  /**
   * Override of the toString method of java.
   *
   * @return The string to display.
   */
  String toString();

  /**
   * Processes a request send to the specific implementation.
   */
  void process();
}
