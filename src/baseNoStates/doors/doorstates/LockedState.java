package baseNoStates.doors.doorstates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baseNoStates.doors.Door;

/**
 * The LockedState class represents the state of a door when it is locked.
 *
 * This class is the implementation of the abstract class DoorState for the State Pattern
 */
public class LockedState extends DoorState {
  private static final Logger milestone1 = LoggerFactory.getLogger("firstMilestone");


  public LockedState(Door door) {
    super(door);
    name = States.LOCKED;
  }

  /**
   * Notifies the user that the door is locked and cannot be opened.
   */
  @Override
  public void open() {
    milestone1.warn("Can't open the door " + door.getId() +", it's locked");
  }

  /**
   * Notifies the user that the door is already closed.
   */
  @Override
  public void close() {
    milestone1.warn("The door " +door.getId()+ " is already closed and locked");
  }

  /**
   * Notifies the user that the door is already locked.
   */
  @Override
  public void lock() {
    milestone1.warn("The door "+ door.getId() +" is already locked");
  }

  /**
   * Since the door is locked, we notify the user that the door is now unlocked and assigns the UnlockedState instance.
   */
  @Override
  public void unlock() {
    milestone1.info("Unlocking the door " +door.getId());
    door.setState(new UnlockedState(door));
  }

  /**
   * Unlocks the door for 10 seconds, assigning the UnlockShortlyState instance.
   */
  @Override
  public void unlockShortly() {
    milestone1.info("Unlocking "+ door.getId() +" the door for 10 seconds");
    door.setState(new UnlockShortlyState(door));
  }
}
