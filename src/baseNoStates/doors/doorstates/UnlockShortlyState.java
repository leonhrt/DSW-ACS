package baseNoStates.doors.doorstates;

import baseNoStates.doors.Door;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;

/**
 * The UnlockShortlyState class represents the state of a door
 * when it is unlocked shortly.
 * It extends 'DoorState' and implements the 'Observer'
 * interface to receive updates from the clock.
 *
 * This class is a part of the Observer Pattern.
 */
public class UnlockShortlyState extends DoorState implements Observer {
  private LocalTime clockStartTime; // The moment when the door was unlocked shortly
  private Clock clock;              // The clock used to track the time
  private final int WAIT_TIME = 10; // The time in seconds to wait before unlocking the door

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
      System.out.println("Opening the door...");
      door.setClosed(false);
    } else {
      System.out.println("The door is already open");
    }
  }

  /**
   * If the door is closed, notifies the user that the door is already closed.
   * Otherwise, closes the door.
   */
  @Override
  public void close() {
    if (door.isClosed()) {
      System.out.println("The door is already closed");
    } else {
      System.out.println("Closing the door...");
      door.setClosed(true);
    }
  }

  /**
   * Notifies the user that the door will be locked automatically in 10 seconds.
   */
  @Override
  public void lock() {
    System.out.println("The door will be locked in 10 seconds automatically, be patient");
  }

  /**
   * Notifies the user that the door is unlocked temporarily.
   */
  @Override
  public void unlock() {
    System.out.println("The door is unlocked temporarily, so can't unlock");
  }

  /**
   * Notifies the user that the door is already in unlock shortly state.
   */
  @Override
  public void unlockShortly() {
    System.out.println("The door is already in unlock shortly state");
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
    if (elapsedTime < WAIT_TIME) {
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

    System.out.println("The door " + door.getId() + " is now in " + state + " state");

    // Delete the observer from the list of observers, since it is no longer needed.
    clock.deleteObserver(this);
  }
}
