package basenostates.requests;

import org.json.JSONObject;


/**
 * The Request interface defines the contract for handling requests
 * within the system.
 */
public interface Request {
  JSONObject answerToJson();

  String toString();

  void process();
}
