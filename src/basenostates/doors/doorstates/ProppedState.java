package basenostates.doors.doorstates;

import basenostates.doors.Door;

/**
 * The ProppedState class represents the state of a door when it is propped.
 *
 * This class is the implementation of the abstract class DoorState for the State Pattern
 */
public class ProppedState extends DoorState {
  public ProppedState(Door door) {
    super(door);
    name = States.PROPPED;
  }

  /**
   * Notifies the user that the door is propped and cannot be opened.
   */
  @Override
  public void open() {
    System.out.println("I mean, the door is open, but propped");
  }

  /**
   * Notifies the user that te door is going to be closed, sets the door to closed and sets the new state (Locked).
   */
  @Override
  public void close() {
    System.out.println("Closing the door...");
    door.setState(new LockedState(door));
    door.setClosed(true);
  }

  /**
   * Notifies the user that the door because it's propped.
   */
  @Override
  public void lock() {
    System.out.println("Can't lock the door because it's propped");
  }

  /**
   * Notifies the user that the door is propped, so it can't be unlocked.
   */
  @Override
  public void unlock() {
    System.out.println("It's propped right now, can't unlock");
  }

  /**
   * Notifies the user that the door is propped and can't be unlocked shortly.
   */
  @Override
  public void unlockShortly() {
    System.out.println("Can't unlock shortly because it's propped");
  }
}
