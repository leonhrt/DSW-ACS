package basenostates.doors.doorstates;

import basenostates.doors.Door;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The UnlockShortlyState class represents the state of a door
 * when it is unlocked shortly.
 * It extends 'DoorState' and implements the 'Observer'
 * interface to receive updates from the clock.
 *
 * <p>This class is a part of the Observer Pattern.
 * Also, this class is the implementation of the abstract class DoorState for the State Pattern
 */
public class UnlockShortlyState extends DoorState implements Observer {
  private LocalTime clockStartTime; // The moment when the door was unlocked shortly
  private Clock clock;              // The clock used to track the time

  private static final Logger milestone1 = LoggerFactory.getLogger("firstMilestone");

  /**
   * Constructs a new UnlockShortlyState with the specified door.
   * Gets the singleton instance of the clock.
   * Adds the observer to the list of observers in the Clock class.
   *
   * @param door The door associated with this state
   */
  public UnlockShortlyState(Door door) {
    super(door);
    name = States.UNLOCKED_SHORTLY;
    clock = Clock.getInstance();
    clock.addObserver(this);
    clockStartTime = LocalTime.now();
  }

  /**
   * If the door is closed, opens it.
   * Otherwise, notifies the user that the door is already open.
   */
  @Override
  public void open() {
    if (door.isClosed()) {
      milestone1.info("Opening the door..." + door.getId());
      door.setClosed(false);
    } else {
      milestone1.warn("The door " + door.getId() + " is already open");
    }
  }

  /**
   * If the door is closed, notifies the user that the door is already closed.
   * Otherwise, closes the door.
   */
  @Override
  public void close() {
    if (door.isClosed()) {
      milestone1.warn("The door " + door.getId() + " is already closed");
    } else {
      milestone1.info("Closing the door " + door.getId());
      door.setClosed(true);
    }
  }

  /**
   * Notifies the user that the door will be locked automatically in 10 seconds.
   */
  @Override
  public void lock() {
    milestone1.info("The door " + door.getId()
        + " will be locked in 10 seconds automatically, be patient");
  }

  /**
   * Notifies the user that the door is unlocked temporarily.
   */
  @Override
  public void unlock() {
    milestone1.warn("The door " + door.getId() + " is unlocked temporarily, so can't unlock");
  }

  /**
   * Notifies the user that the door is already in unlock shortly state.
   */
  @Override
  public void unlockShortly() {
    milestone1.warn("The door " + door.getId() + " is already in unlock shortly state");
  }

  /**
   * Updates the door state based on the elapsed time since the start of the unlock shortly state.
   * in order to be aware of the state of the door, we implement the Observer Pattern.
   * The door will wait 10 seconds,
   * after 10 seconds if the door is closed it will
   * change the door to Locked, otherwise will set the door Propped.
   *
   * @param o   The observable object (clock).
   * @param arg The argument passed to notifyObservers.
   */
  @Override
  public void update(Observable o, Object arg) {
    // Get the current time
    LocalTime currentTime = clock.getDate().toLocalTime();
    // Calculate the elapsed time since the start of the unlock shortly state
    int elapsedTime = (int) clockStartTime.until(currentTime, ChronoUnit.SECONDS);

    // Check if the specified time has elapsed
    // The time in seconds to wait before unlocking the door
    int waitTime = 10;
    if (elapsedTime < waitTime) {
      return;
    }

    String state;
    // If the door is closed, change the state to Locked
    // Otherwise, change the state to Propped
    if (door.isClosed()) {
      door.setState(new LockedState(door));
      state = States.LOCKED;
    } else {
      door.setState(new ProppedState(door));
      state = States.PROPPED;
    }

    milestone1.debug("The door " + door.getId() + " is now in " + state + " state");

    // Delete the observer from the list of observers, since it is no longer needed.
    clock.deleteObserver(this);
  }
}
