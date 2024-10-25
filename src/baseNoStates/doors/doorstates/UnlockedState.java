package baseNoStates.doors.doorstates;

import baseNoStates.doors.Door;

/**
 * The UnlockedState class represents the state of a door when it is unlocked.
 *
 * This class is the implementation of the abstract class DoorState for the State Pattern
 */
public class UnlockedState extends DoorState {
  public UnlockedState(Door door) {
    super(door);
    name = States.UNLOCKED;
  }

  /**
   * Checks if the door is closed.
   * If it is, it opens the door.
   * Otherwise, it notifies the user that the door is already open.
   */
  @Override
  public void open() {
    if (door.isClosed()) {        //Check if the door is closed so it can be opened
      System.out.println("Opening the door...");
      door.setClosed(false);
    } else {
      System.out.println("The door is already open");
    }
  }

  /**
   * Checks if the door is open.
   * If it is, it closes the door.
   * Otherwise, it notifies the user that the door is already closed.
   */
  @Override
  public void close() {
    if (!door.isClosed()) {       //Check if the door is open so it can be closed
      System.out.println("Closing the door...");
      door.setClosed(true);
    } else {
      System.out.println("The door is already closed");
    }
  }

  /**
   * As the actual state is unlocked, we set the door's state to
   * locked with a new LockedState
   */
  @Override
  public void lock() {
    if (!door.isClosed()) {       //Door cannot be locked if it's open
      System.out.println("Can't lock the door because it's open");
    } else {
      System.out.println("The door is already locked");
      door.setState(new LockedState(door));
    }
  }

  /**
   * Notifies the user that the door is already unlocked.
   */
  @Override
  public void unlock() {
    System.out.println("The door is already unlocked");
  }

  /**
   * Notifies the user that a unlocked door cannot be unlocked shortly.
   */
  @Override
  public void unlockShortly() {
    System.out.println("The door is already unlocked, can't unlock shortly");
  }
}
