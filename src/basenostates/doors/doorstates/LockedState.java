package basenostates.doors.doorstates;

import basenostates.doors.Door;

/**
 * The LockedState class represents the state of a door when it is locked.
 *
 * This class is the implementation of the abstract class DoorState for the State Pattern
 */
public class LockedState extends DoorState {
  public LockedState(Door door) {
    super(door);
    name = States.LOCKED;
  }

  /**
   * Notifies the user that the door is locked and cannot be opened.
   */
  @Override
  public void open() {
    System.out.println("Can't open the door, it's locked");
  }

  /**
   * Notifies the user that the door is already closed.
   */
  @Override
  public void close() {
    System.out.println("The door is already closed and locked");
  }

  /**
   * Notifies the user that the door is already locked.
   */
  @Override
  public void lock() {
    System.out.println("The door is already locked");
  }

  /**
   * Since the door is locked, we notify the user that the door is now unlocked and assigns the UnlockedState instance.
   */
  @Override
  public void unlock() {
    System.out.println("Unlocking the door");
    door.setState(new UnlockedState(door));
  }

  /**
   * Unlocks the door for 10 seconds, assigning the UnlockShortlyState instance.
   */
  @Override
  public void unlockShortly() {
    System.out.println("Unlocking the door for 10 seconds");
    door.setState(new UnlockShortlyState(door));
  }
}
