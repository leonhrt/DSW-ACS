package basenostates;

import basenostates.requests.Request;
import basenostates.requests.RequestArea;
import basenostates.requests.RequestReader;
import basenostates.requests.RequestRefresh;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Based on
// https://www.ssaurel.com/blog/create-a-simple-http-web-server-in-java
// http://www.jcgonzalez.com/java-socket-mini-server-http-example

/**
 * Class that connects the front-end (HTML + javascript) with the back-end (Java).
 */
public class WebServer {
  private static final int PORT = 8080; // port to listen connection
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd'T'HH:mm");

  //private static final Logger milestone1 = LoggerFactory.getLogger("firstMilestone");
  //private static final Logger milestone2 = LoggerFactory.getLogger("secondtMilestone");
  private static final Logger globalLog = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


  /**
   * Constructor that create a WebServer instance initiating a ServerSocket to allow
   * connection.
   * Tries to connect, if successful, connects and notifies the user, if not, notifies the user
   * about the error.
   */
  public WebServer() {
    try {
      ServerSocket serverConnect = new ServerSocket(PORT);
      globalLog.debug("Server started.\nListening for connections on port : " + PORT + " ...\n");
      // we listen until user halts server execution
      while (true) {
        // each client connection will be managed in a dedicated Thread
        new SocketThread(serverConnect.accept());
        // create dedicated thread to manage the client connection
      }
    } catch (IOException e) {
      globalLog.warn("Server Connection error : " + e.getMessage());
    }
  }


  private class SocketThread extends Thread {
    // as an inner class, SocketThread sees WebServer attributes
    private final Socket insocked; // client connection via Socket class

    SocketThread(Socket insocket) {
      this.insocked = insocket;
      this.start();
    }

    @Override
    public void run() {
      // we manage our particular client connection
      BufferedReader in;
      PrintWriter out;
      String resource;

      try {
        // we read characters from the client via input stream on the socket
        in = new BufferedReader(new InputStreamReader(insocked.getInputStream()));
        // we get character output stream to client
        out = new PrintWriter(insocked.getOutputStream());
        // get first line of the request from the client
        String input = in.readLine();
        // we parse the request with a string tokenizer

        globalLog.debug("sockedthread : " + input);

        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
        if (!method.equals("GET")) {
          globalLog.debug("501 Not Implemented : " + method + " method.");
        } else {
          // what comes after "localhost:8080"
          resource = parse.nextToken();
          globalLog.debug("input " + input);
          globalLog.debug("method " + method);
          globalLog.debug("resource " + resource);

          parse = new StringTokenizer(resource, "/[?]=&");
          int i = 0;
          String[] tokens = new String[20]; // more than the actual number of parameters
          while (parse.hasMoreTokens()) {
            tokens[i] = parse.nextToken();
            globalLog.debug(i + " " + tokens[i]);
            i++;
          }

          // Here is where we send the request and get the answer inside it
          Request request = makeRequest(tokens);
          if (request != null) {
            String typeRequest = tokens[0];
            globalLog.debug("created request " + typeRequest + " " + request);
            request.process();
            globalLog.debug("processed request " + typeRequest + " " + request);
            // Make the answer as a JSON string, to be sent to the Javascript client
            String answer = makeJsonAnswer(request);
            globalLog.debug("answer\n" + answer);
            // Here we send the response to the client
            out.println(answer);
            out.flush(); // flush character output stream buffer
          }
        }

        in.close();
        out.close();
        insocked.close(); // we close socket connection
      } catch (Exception e) {
        globalLog.warn("Exception : " + e);
      }
    }

    private Request makeRequest(String[] tokens) {
      // always return request because it contains the answer for the Javascript client
      globalLog.debug("tokens : ");
      for (String token : tokens) {
        globalLog.debug(token + ", ");
      }
      System.out.println();

      Request request;
      // assertions below evaluated to false won't stop the webserver, just print an
      // assertion error, maybe because the webserver runs in a socked thread
      switch (tokens[0]) {
        case "refresh":
          request = new RequestRefresh();
          break;
        case "reader":
          request = makeRequestReader(tokens);
          break;
        case "area":
          request = makeRequestArea(tokens);
          break;
        case "get_children":
          //TODO: this is to be implemented when programming the mobile app in Flutter
          // in order to navigate the hierarchy of partitions, spaces and doors
          assert false : "request get_children is not yet implemented";
          request = null;
          System.exit(-1);
          break;
        default:
          // just in case we change the user interface or the simulator
          assert false : "unknown request " + tokens[0];
          request = null;
          System.exit(-1);
      }
      return request;
    }

    private RequestReader makeRequestReader(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], formatter);
      String doorId = tokens[8];
      return new RequestReader(credential, action, dateTime, doorId);
    }

    private RequestArea makeRequestArea(String[] tokens) {
      String credential = tokens[2];
      String action = tokens[4];
      LocalDateTime dateTime = LocalDateTime.parse(tokens[6], formatter);
      String areaId = tokens[8];
      return new RequestArea(credential, action, dateTime, areaId);
    }

    private String makeHeaderAnswer() {
      String answer = "";
      answer += "HTTP/1.0 200 OK\r\n";
      answer += "Content-type: application/json\r\n";
      answer += "Access-Control-Allow-Origin: *\r\n";
      // SUPERIMPORTANT to avoid the CORS problem :
      // "Cross-Origin Request Blocked: The Same Origin Policy disallows reading
      // the remote resource..."
      answer += "\r\n"; // blank line between headers and content, very important !
      return answer;
    }

    private String makeJsonAnswer(Request request) {
      String answer = makeHeaderAnswer();
      answer += request.answerToJson().toString();
      return answer;
    }

  }

}